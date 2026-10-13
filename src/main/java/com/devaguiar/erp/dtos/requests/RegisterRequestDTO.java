package com.devaguiar.erp.dtos.requests;

import com.devaguiar.erp.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO(
        @NotBlank String login,
        @NotBlank String senha,
        @NotNull UserRole role) {
}