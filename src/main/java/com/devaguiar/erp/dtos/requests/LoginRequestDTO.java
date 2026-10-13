package com.devaguiar.erp.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank String login,
        @NotBlank String senha) {
}