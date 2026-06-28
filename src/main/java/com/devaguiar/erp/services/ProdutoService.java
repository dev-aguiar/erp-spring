package com.devaguiar.erp.services;

import com.devaguiar.erp.dtos.requests.ProdutoRequestDTO;
import com.devaguiar.erp.dtos.responses.ProdutoResponseDTO;
import com.devaguiar.erp.entities.Produto;
import com.devaguiar.erp.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoResponseDTO createProduto(ProdutoRequestDTO data) {
        Produto produto = new Produto(data);
        produtoRepository.save(produto);
        return new ProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO updateProduto(Long id, ProdutoRequestDTO data) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));

        produto.setNome(data.nome());
        produto.setPreco(data.preco());
        produto.setQuantidade(data.quantidade());
        produtoRepository.save(produto);
        return new ProdutoResponseDTO(produto);
    }

    public void deleteProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");
        }
        produtoRepository.deleteById(id);
    }

    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository.findAll().stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    public ProdutoResponseDTO findById(Long id) {
        Produto result = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
        return new ProdutoResponseDTO(result);
    }
}
