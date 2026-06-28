package com.devaguiar.erp.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record VendedorRequestDTO(
        @NotBlank
        String nome,

        @NotNull
        LocalDate dataNascimento) {

}
