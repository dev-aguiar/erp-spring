package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.AdicionarProdutoPedidoRequestDTO;
import com.devaguiar.erp.dtos.requests.PedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.PedidoResponseDTO;
import com.devaguiar.erp.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public PedidoRequestDTO createPedido(@RequestBody PedidoRequestDTO data) {
        return pedidoService.createPedido(data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public PedidoRequestDTO updatePedido(@PathVariable Long id, @RequestBody PedidoRequestDTO data) {
        return pedidoService.updatePedido(id, data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/adicionar-produto")
    public ResponseEntity<String> adicionarProdutoAoPedido(@RequestBody AdicionarProdutoPedidoRequestDTO data) {
        pedidoService.adicionarProdutoAoPedido(data);
        return ResponseEntity.ok("Produto adicionado ao pedido com sucesso!");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<PedidoResponseDTO> findAll() {
        return pedidoService.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public PedidoResponseDTO findById(@PathVariable Long id) {
        return pedidoService.findById(id);
    }
}
