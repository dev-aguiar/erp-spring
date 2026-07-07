package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.ItemPedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.ItemPedidoResponseDTO;
import com.devaguiar.erp.services.ItemPedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos/{pedidoId}/itens")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @PostMapping
    public ResponseEntity<ItemPedidoResponseDTO> createItemPedido(@PathVariable Long pedidoId,
                                                                 @Valid @RequestBody ItemPedidoRequestDTO data) {
        ItemPedidoResponseDTO create = itemPedidoService.createItemPedido(pedidoId, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping
    public List<ItemPedidoResponseDTO> getAllItems(@PathVariable Long pedidoId) {
        return itemPedidoService.getAllItemsByPedido(pedidoId);
    }
}
