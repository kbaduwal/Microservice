package com.usermanagementservice.playload;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private int status;
    private List<String> errors = new ArrayList<>();

    public ErrorResponse(LocalDateTime now, String validationFailed, String description, int value) {
        this.timestamp = now;
        this.message = description;
        this.details = validationFailed;
        this.status = value;
    }

    public void addValidationError(String error) {
        errors.add(error);
    }
}
