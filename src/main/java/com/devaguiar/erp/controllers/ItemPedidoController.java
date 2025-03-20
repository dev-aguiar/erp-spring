package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.ItemPedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.ItemPedidoResponseDTO;
import com.devaguiar.erp.services.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ItemPedidoResponseDTO createItemPedido(@PathVariable Long pedidoId,
                                                  @RequestBody ItemPedidoRequestDTO data) {
        return itemPedidoService.createItemPedido(pedidoId, data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ItemPedidoResponseDTO> getAllItems(@PathVariable Long pedidoId) {
        return itemPedidoService.getAllItemsByPedido(pedidoId);
    }
}
