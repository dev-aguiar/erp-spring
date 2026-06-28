package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.ItemPedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.ItemPedidoResponseDTO;
import com.devaguiar.erp.services.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos/{pedidoId}/itens")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    @Autowired
    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @PostMapping
    public ResponseEntity<ItemPedidoResponseDTO> createItemPedido(@PathVariable Long pedidoId,
                                                                 @RequestBody ItemPedidoRequestDTO data) {
        ItemPedidoResponseDTO create = itemPedidoService.createItemPedido(pedidoId, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping
    public List<ItemPedidoResponseDTO> getAllItems(@PathVariable Long pedidoId) {
        return itemPedidoService.getAllItemsByPedido(pedidoId);
    }
}
