package com.slendershield.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer details and Account details"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer",example = "John Doe"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "Name should be between 5 and 30 characters")
    private String name;

    @Schema(
            description = "Email of the customer",example = "JohnDoe@example.com"
    )
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid value")
    private String email;

    @Schema(
            description = "Mobile Number of the customer",example = "+91-9876543210"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountsDto accountsDto;
}
