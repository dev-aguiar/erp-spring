package com.devaguiar.erp.services;

import com.devaguiar.erp.dtos.requests.ProdutoRequestDTO;
import com.devaguiar.erp.dtos.responses.ProdutoResponseDTO;
import com.devaguiar.erp.entities.Produto;
import com.devaguiar.erp.exceptions.ResourceNotFoundException;
import com.devaguiar.erp.repositories.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void createProduto_Success() {
        ProdutoRequestDTO request = new ProdutoRequestDTO(
                "Produto Teste",
                BigDecimal.valueOf(50.00),
                10);

        ProdutoResponseDTO response = produtoService.createProduto(request);

        assertNotNull(response);
        assertEquals("Produto Teste", response.nome());
        assertEquals(BigDecimal.valueOf(50.00), response.preco());
        assertEquals(10, response.quantidade());
        verify(produtoRepository).save(any(Produto.class));
    }

    @Test
    void updateProduto_Success() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setPreco(BigDecimal.valueOf(50.00));
        produto.setQuantidade(10);

        ProdutoRequestDTO request = new ProdutoRequestDTO(
                "Produto Atualizado",
                BigDecimal.valueOf(75.00),
                20);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        ProdutoResponseDTO response = produtoService.updateProduto(1L, request);

        assertEquals("Produto Atualizado", response.nome());
        assertEquals(BigDecimal.valueOf(75.00), response.preco());
        assertEquals(20, response.quantidade());
        verify(produtoRepository).save(produto);
    }

    @Test
    void updateProduto_NotFound() {
        ProdutoRequestDTO request = new ProdutoRequestDTO(
                "Produto Atualizado",
                BigDecimal.valueOf(75.00),
                20);

        when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> produtoService.updateProduto(99L, request));
        verify(produtoRepository, never()).save(any());
    }

    @Test
    void findById_Success() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setPreco(BigDecimal.valueOf(50.00));
        produto.setQuantidade(10);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        ProdutoResponseDTO response = produtoService.findById(1L);

        assertEquals(1L, response.id());
        assertEquals("Produto Teste", response.nome());
    }

    @Test
    void findById_NotFound() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> produtoService.findById(1L));

        assertEquals("Produto não encontrado!", ex.getMessage());
    }

    @Test
    void findAll_Success() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");

        when(produtoRepository.findAll()).thenReturn(List.of(produto));

        List<ProdutoResponseDTO> response = produtoService.findAll();

        assertEquals(1, response.size());
        assertEquals("Produto Teste", response.get(0).nome());
    }

    @Test
    void deleteProduto_Success() {
        when(produtoRepository.existsById(1L)).thenReturn(true);

        produtoService.deleteProduto(1L);

        verify(produtoRepository).deleteById(1L);
    }

    @Test
    void deleteProduto_NotFound() {
        when(produtoRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> produtoService.deleteProduto(99L));
        verify(produtoRepository, never()).deleteById(anyLong());
    }
}
