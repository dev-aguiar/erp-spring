package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.ClienteRequestDTO;
import com.devaguiar.erp.dtos.responses.ClienteResponseDTO;
import com.devaguiar.erp.entities.Cliente;
import com.devaguiar.erp.exceptions.ResourceNotFoundException;
import com.devaguiar.erp.services.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ClienteService clienteService;

    @Test
    void createCliente_Success() throws Exception {
        ClienteRequestDTO request =
                new ClienteRequestDTO("Maria", "maria@email.com", "11999999999", "Rua A");

        ClienteResponseDTO response =
                new ClienteResponseDTO(1L, "Maria", "maria@email.com", "11999999999", "Rua A", List.of());
        when(clienteService.createCliente(any(ClienteRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Maria"));
    }

    @Test
    void createCliente_InvalidData_ReturnsBadRequest() throws Exception {
        ClienteRequestDTO invalid = new ClienteRequestDTO("", "email-invalido", "", "");

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findById_NotFound_Returns404() throws Exception {
        when(clienteService.findById(eq(999L)))
                .thenThrow(new ResourceNotFoundException("Cliente não encontrado!"));

        mockMvc.perform(get("/clientes/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCliente_Success() throws Exception {
        ClienteRequestDTO request =
                new ClienteRequestDTO("João", "joao@email.com", "11988888888", "Rua B");

        ClienteResponseDTO response =
                new ClienteResponseDTO(2L, "João", "joao@email.com", "11988888888", "Rua B", List.of());

        when(clienteService.updateCliente(eq(2L), any(ClienteRequestDTO.class))).thenReturn(response);

        mockMvc.perform(put("/clientes/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nome").value("João"));
    }
}
