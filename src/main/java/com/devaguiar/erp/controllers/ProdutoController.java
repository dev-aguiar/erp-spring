package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.ProdutoRequestDTO;
import com.devaguiar.erp.dtos.responses.ProdutoResponseDTO;
import com.devaguiar.erp.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ProdutoRequestDTO createProduto(@RequestBody ProdutoRequestDTO data) {
        return produtoService.createProduto(data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(value = "/{id}")
    public ProdutoRequestDTO updateProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO data) {
        return produtoService.updateProduto(id, data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/{id}")
    public void deleteProduto(@PathVariable Long id) {
        produtoService.deleteProduto(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ProdutoResponseDTO> findAll() {
        return produtoService.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{id}")
    public ProdutoResponseDTO findById(@PathVariable Long id) {
        return produtoService.findById(id);
    }
}
