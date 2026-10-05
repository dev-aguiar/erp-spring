package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.ProdutoRequestDTO;
import com.devaguiar.erp.dtos.responses.ProdutoResponseDTO;
import com.devaguiar.erp.exceptions.ResourceNotFoundException;
import com.devaguiar.erp.services.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProdutoService produtoService;

    @Test
    void createProduto_Success() throws Exception {
        ProdutoRequestDTO request =
                new ProdutoRequestDTO("Produto Teste", BigDecimal.valueOf(50.00), 10);

        ProdutoResponseDTO response =
                new ProdutoResponseDTO(1L, "Produto Teste", BigDecimal.valueOf(50.00), 10);
        when(produtoService.createProduto(any(ProdutoRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Produto Teste"));
    }

    @Test
    void createProduto_InvalidData_ReturnsBadRequest() throws Exception {
        ProdutoRequestDTO invalid =
                new ProdutoRequestDTO("", BigDecimal.valueOf(-5.00), -1);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findById_NotFound_Returns404() throws Exception {
        when(produtoService.findById(eq(999L)))
                .thenThrow(new ResourceNotFoundException("Produto não encontrado!"));

        mockMvc.perform(get("/produtos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateProduto_Success() throws Exception {
        ProdutoRequestDTO request =
                new ProdutoRequestDTO("Produto Atualizado", BigDecimal.valueOf(75.00), 20);

        ProdutoResponseDTO response =
                new ProdutoResponseDTO(2L, "Produto Atualizado", BigDecimal.valueOf(75.00), 20);

        when(produtoService.updateProduto(eq(2L), any(ProdutoRequestDTO.class))).thenReturn(response);

        mockMvc.perform(put("/produtos/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nome").value("Produto Atualizado"));
    }

    @Test
    void deleteProduto_Success() throws Exception {
        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().isNoContent());
    }
}
