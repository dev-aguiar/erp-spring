package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.PedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.ClienteResumidoDTO;
import com.devaguiar.erp.dtos.responses.PedidoResponseDTO;
import com.devaguiar.erp.dtos.responses.VendedorResumidoDTO;
import com.devaguiar.erp.enums.FormaPagamento;
import com.devaguiar.erp.enums.StatusPedido;
import com.devaguiar.erp.exceptions.ResourceNotFoundException;
import com.devaguiar.erp.services.PedidoService;
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

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PedidoService pedidoService;

    @Test
    void createPedido_Success() throws Exception {
        PedidoRequestDTO request = new PedidoRequestDTO(
                1L, 2L, LocalDate.of(2026, 7, 5),
                StatusPedido.EM_ANDAMENTO, FormaPagamento.PIX);

        PedidoResponseDTO response = new PedidoResponseDTO(
                10L,
                new ClienteResumidoDTO(1L, "Maria"),
                new VendedorResumidoDTO(2L, "João"),
                LocalDate.of(2026, 7, 5),
                FormaPagamento.PIX,
                StatusPedido.EM_ANDAMENTO);
        when(pedidoService.createPedido(any(PedidoRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.cliente.nome").value("Maria"))
                .andExpect(jsonPath("$.statusPedido").value(StatusPedido.EM_ANDAMENTO.getValor()));
    }

    @Test
    void createPedido_InvalidData_ReturnsBadRequest() throws Exception {
        PedidoRequestDTO invalid = new PedidoRequestDTO(
                null, null, null, null, null);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findById_NotFound_Returns404() throws Exception {
        when(pedidoService.findById(eq(999L)))
                .thenThrow(new ResourceNotFoundException("Pedido não encontrado!"));

        mockMvc.perform(get("/pedidos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePedido_Success() throws Exception {
        mockMvc.perform(delete("/pedidos/1"))
                .andExpect(status().isNoContent());
    }
}
