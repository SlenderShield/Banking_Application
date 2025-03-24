package com.slendershield.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response details"
)
public class ErrorResponseDto {

    @Schema(
            description = "API path invoked by client/customer"
    )
    private String apiPath;

    @Schema(
            description = "Error status code"
    )
    private HttpStatus status;

    @Schema(
            description = "Error message to be displayed to the customer"
    )
    private String errorMessage;

    @Schema(
            description = "Error occurred time"
    )
    private LocalDateTime errorTime;
}
