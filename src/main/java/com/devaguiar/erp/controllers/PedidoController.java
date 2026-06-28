package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.AdicionarProdutoPedidoRequestDTO;
import com.devaguiar.erp.dtos.requests.PedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.PedidoResponseDTO;
import com.devaguiar.erp.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> createPedido(@Valid @RequestBody PedidoRequestDTO data) {
        PedidoResponseDTO created = pedidoService.createPedido(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> updatePedido(@PathVariable Long id, @Valid @RequestBody PedidoRequestDTO data) {
        PedidoResponseDTO updated = pedidoService.updatePedido(id, data);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/adicionar-produto")
    public ResponseEntity<String> adicionarProdutoAoPedido(@Valid @RequestBody AdicionarProdutoPedidoRequestDTO data) {
        pedidoService.adicionarProdutoAoPedido(data);
        return ResponseEntity.ok("Produto adicionado ao pedido com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<PedidoResponseDTO> findAll() {
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }
}
