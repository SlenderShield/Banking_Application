package com.slendershield.accounts.controller;

import com.slendershield.accounts.constants.AccountsConstants;
import com.slendershield.accounts.dto.CustomerDto;
import com.slendershield.accounts.dto.ErrorResponseDto;
import com.slendershield.accounts.dto.ResponseDto;
import com.slendershield.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//    Documentation using OPENAPI 3.0 specification.
@Tag(
        name = "CRUD REST API for Accounts",
        description = "CRUD REST API for Accounts microservice"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @Operation(
            summary = "Create a new account",
            description = "Create a new account with the provided details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP status created. Account created successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status internal server error. Account update failed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )}
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(
                        AccountsConstants.STATUS_201,
                        AccountsConstants.MESSAGE_201
                ));
    }

    @Operation(
            summary = "Fetch account details",
            description = "Fetch account details with the provided mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status ok. Account fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status internal server error. Account update failed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )}
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
                                                           String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update account details",
            description = "Rest API to update account details & customer details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status ok. Account updated successfully"
            ),
            @ApiResponse(
                    responseCode = "471",
                    description = "HTTP status failed. Account update failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status internal server error. Account update failed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )}
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(
                    AccountsConstants.STATUS_200,
                    AccountsConstants.MESSAGE_200
            ));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                AccountsConstants.STATUS_417,
                AccountsConstants.MESSAGE_417_UPDATE
        ));
    }

    @Operation(
            summary = "Delete account details",
            description = "Rest API to delete account details & customer details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status ok. Account deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "471",
                    description = "HTTP status failed. Account deletion failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status internal server error. Account deletion failed"
            )}
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
                                                            String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
    }
}
