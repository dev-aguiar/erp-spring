package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.ItemPedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.ItemPedidoResponseDTO;
import com.devaguiar.erp.services.ItemPedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemPedidoController.class)
class ItemPedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ItemPedidoService itemPedidoService;

    @Test
    void createItemPedido_Success() throws Exception {
        ItemPedidoRequestDTO request =
                new ItemPedidoRequestDTO(1L, 2L, 5, BigDecimal.valueOf(50.00));

        ItemPedidoResponseDTO response =
                new ItemPedidoResponseDTO(1L, 2L, 5, BigDecimal.valueOf(50.00));
        when(itemPedidoService.createItemPedido(eq(1L), any(ItemPedidoRequestDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/pedidos/1/itens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.produtoId").value(2))
                .andExpect(jsonPath("$.quantidade").value(5));
    }

    @Test
    void createItemPedido_InvalidData_ReturnsBadRequest() throws Exception {
        ItemPedidoRequestDTO invalid =
                new ItemPedidoRequestDTO(null, null, -1, BigDecimal.valueOf(-5.00));

        mockMvc.perform(post("/pedidos/1/itens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllItems_Success() throws Exception {
        ItemPedidoResponseDTO item =
                new ItemPedidoResponseDTO(1L, 2L, 5, BigDecimal.valueOf(50.00));
        when(itemPedidoService.getAllItemsByPedido(eq(1L))).thenReturn(List.of(item));

        mockMvc.perform(get("/pedidos/1/itens"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].produtoId").value(2));
    }
}
