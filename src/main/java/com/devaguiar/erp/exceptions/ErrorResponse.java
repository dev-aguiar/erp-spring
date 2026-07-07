package com.devaguiar.erp.exceptions;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        List<FieldError> fields
) {
    public record FieldError(String field, String message) {}
}