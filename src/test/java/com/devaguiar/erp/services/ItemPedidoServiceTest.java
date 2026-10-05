package com.devaguiar.erp.services;

import com.devaguiar.erp.dtos.requests.ItemPedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.ItemPedidoResponseDTO;
import com.devaguiar.erp.entities.ItemPedido;
import com.devaguiar.erp.entities.Pedido;
import com.devaguiar.erp.entities.Produto;
import com.devaguiar.erp.exceptions.ResourceNotFoundException;
import com.devaguiar.erp.repositories.ItemPedidoRepository;
import com.devaguiar.erp.repositories.PedidoRepository;
import com.devaguiar.erp.repositories.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemPedidoServiceTest {

    @Mock
    private ItemPedidoRepository itemPedidoRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ItemPedidoService itemPedidoService;

    @Test
    void createItemPedido_Success() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setDataPedido(java.time.LocalDate.of(2026, 7, 5));

        Produto produto = new Produto();
        produto.setId(2L);
        produto.setNome("Produto Teste");
        produto.setQuantidade(10);
        produto.setPreco(BigDecimal.valueOf(50.00));

        ItemPedidoRequestDTO request = new ItemPedidoRequestDTO(
                1L, 2L, 5, BigDecimal.valueOf(50.00));

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(produtoRepository.findById(2L)).thenReturn(Optional.of(produto));
        when(itemPedidoRepository.save(any(ItemPedido.class))).thenAnswer(invocation -> {
            ItemPedido saved = invocation.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        ItemPedidoResponseDTO response = itemPedidoService.createItemPedido(1L, request);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals(2L, response.produtoId());
        assertEquals(5, response.quantidade());
        assertEquals(BigDecimal.valueOf(50.00), response.valorUnitario());
        verify(itemPedidoRepository).save(any(ItemPedido.class));
    }

    @Test
    void createItemPedido_Pedido_NotFound() {
        ItemPedidoRequestDTO request = new ItemPedidoRequestDTO(
                99L, 2L, 5, BigDecimal.valueOf(50.00));

        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> itemPedidoService.createItemPedido(99L, request));

        assertEquals("Pedido não encontrado", ex.getMessage());
        verify(itemPedidoRepository, never()).save(any());
    }

    @Test
    void createItemPedido_Produto_NotFound() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        ItemPedidoRequestDTO request = new ItemPedidoRequestDTO(
                1L, 99L, 5, BigDecimal.valueOf(50.00));

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> itemPedidoService.createItemPedido(1L, request));

        assertEquals("Produto não encontrado", ex.getMessage());
        verify(itemPedidoRepository, never()).save(any());
    }

    @Test
    void getAllItemsByPedido_Success() {
        Produto produto = new Produto();
        produto.setId(2L);

        ItemPedido item = new ItemPedido();
        item.setId(1L);
        item.setProduto(produto);
        item.setQuantidade(5);
        item.setValorUnitario(BigDecimal.valueOf(50.00));

        when(itemPedidoRepository.findByPedidoId(1L)).thenReturn(List.of(item));

        List<ItemPedidoResponseDTO> response = itemPedidoService.getAllItemsByPedido(1L);

        assertEquals(1, response.size());
        assertEquals(1L, response.get(0).id());
        assertEquals(2L, response.get(0).produtoId());
    }
}
