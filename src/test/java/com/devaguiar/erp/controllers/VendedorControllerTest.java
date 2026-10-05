package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.VendedorRequestDTO;
import com.devaguiar.erp.dtos.responses.VendedorResponseDTO;
import com.devaguiar.erp.exceptions.ResourceNotFoundException;
import com.devaguiar.erp.services.VendedorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VendedorController.class)
class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VendedorService vendedorService;

    @Test
    void createVendedor_Success() throws Exception {
        VendedorRequestDTO request =
                new VendedorRequestDTO("Carlos", LocalDate.of(1990, 5, 15));

        VendedorResponseDTO response =
                new VendedorResponseDTO(1L, "Carlos", LocalDate.of(1990, 5, 15));
        when(vendedorService.createVendedor(any(VendedorRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/vendedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Carlos"));
    }

    @Test
    void createVendedor_InvalidData_ReturnsBadRequest() throws Exception {
        VendedorRequestDTO invalid = new VendedorRequestDTO("", null);

        mockMvc.perform(post("/vendedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findById_NotFound_Returns404() throws Exception {
        when(vendedorService.findById(eq(999L)))
                .thenThrow(new ResourceNotFoundException("Vendedor não encontrado!"));

        mockMvc.perform(get("/vendedores/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateVendedor_Success() throws Exception {
        VendedorRequestDTO request =
                new VendedorRequestDTO("Carlos Silva", LocalDate.of(1991, 6, 20));

        VendedorResponseDTO response =
                new VendedorResponseDTO(2L, "Carlos Silva", LocalDate.of(1991, 6, 20));

        when(vendedorService.updateVendedor(eq(2L), any(VendedorRequestDTO.class))).thenReturn(response);

        mockMvc.perform(put("/vendedores/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nome").value("Carlos Silva"));
    }

    @Test
    void deleteVendedor_Success() throws Exception {
        mockMvc.perform(delete("/vendedores/1"))
                .andExpect(status().isNoContent());
    }
}
