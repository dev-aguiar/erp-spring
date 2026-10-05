package com.devaguiar.erp.services;

import com.devaguiar.erp.dtos.requests.AdicionarProdutoPedidoRequestDTO;
import com.devaguiar.erp.dtos.requests.PedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.PedidoResponseDTO;
import com.devaguiar.erp.entities.Cliente;
import com.devaguiar.erp.entities.ItemPedido;
import com.devaguiar.erp.entities.Pedido;
import com.devaguiar.erp.entities.Produto;
import com.devaguiar.erp.entities.Vendedor;
import com.devaguiar.erp.enums.FormaPagamento;
import com.devaguiar.erp.enums.StatusPedido;
import com.devaguiar.erp.exceptions.ResourceNotFoundException;
import com.devaguiar.erp.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private ProdutoRepository produtoRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private VendedorRepository vendedorRepository;
    @Mock
    private ItemPedidoRepository itemPedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void createPedido_Success() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");

        Vendedor vendedor = new Vendedor();
        vendedor.setId(2L);
        vendedor.setNome("João");

        PedidoRequestDTO request = new PedidoRequestDTO(
                1L, 2L, LocalDate.of(2026, 7, 5),
                StatusPedido.EM_ANDAMENTO, FormaPagamento.PIX);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(vendedorRepository.findById(2L)).thenReturn(Optional.of(vendedor));

        PedidoResponseDTO response = pedidoService.createPedido(request);

        assertNotNull(response);
        assertEquals("Maria", response.cliente().nome());
        assertEquals(StatusPedido.EM_ANDAMENTO, response.statusPedido());
        assertEquals(FormaPagamento.PIX, response.formaPagamento());
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void createPedido_Cliente_NotFound() {
        PedidoRequestDTO request = new PedidoRequestDTO(
                99L, 2L, LocalDate.now(),
                StatusPedido.EM_ANDAMENTO, FormaPagamento.PIX);

        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> pedidoService.createPedido(request));
        verify(pedidoRepository, never()).save(any());
    }

    @Test
    void createPedido_Vendedor_NotFound() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        PedidoRequestDTO request = new PedidoRequestDTO(
                1L, 99L, LocalDate.now(),
                StatusPedido.EM_ANDAMENTO, FormaPagamento.PIX);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(vendedorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> pedidoService.createPedido(request));
        verify(pedidoRepository, never()).save(any());
    }

    @Test
    void updatePedido_Success() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");
        Vendedor vendedor = new Vendedor();
        vendedor.setId(2L);

        Pedido pedido = new Pedido(cliente, vendedor, LocalDate.of(2026, 7, 5),
                FormaPagamento.PIX, StatusPedido.EM_ANDAMENTO);
        pedido.setId(10L);

        PedidoRequestDTO request = new PedidoRequestDTO(
                1L, 2L, LocalDate.of(2026, 8, 10),
                StatusPedido.LIBERADO, FormaPagamento.PIX);

        when(pedidoRepository.findById(10L)).thenReturn(Optional.of(pedido));

        PedidoResponseDTO response = pedidoService.updatePedido(10L, request);

        assertEquals(LocalDate.of(2026, 8, 10), response.dataPedido());
        assertEquals(StatusPedido.LIBERADO, response.statusPedido());
        verify(pedidoRepository).save(pedido);
    }

    @Test
    void updatePedido_NotFound() {
        PedidoRequestDTO request = new PedidoRequestDTO(
                1L, 2L, LocalDate.now(),
                StatusPedido.LIBERADO, FormaPagamento.PIX);

        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> pedidoService.updatePedido(99L, request));
        verify(pedidoRepository, never()).save(any());
    }

    @Test
    void adicionarProdutoAoPedido_Success() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        Produto produto = new Produto();
        produto.setId(2L);
        produto.setPreco(BigDecimal.valueOf(50.00));

        AdicionarProdutoPedidoRequestDTO request =
                new AdicionarProdutoPedidoRequestDTO(1L, 2L, 3);

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(produtoRepository.findById(2L)).thenReturn(Optional.of(produto));

        pedidoService.adicionarProdutoAoPedido(request);

        verify(itemPedidoRepository).save(any(ItemPedido.class));
    }

    @Test
    void adicionarProdutoAoPedido_Pedido_NotFound() {
        AdicionarProdutoPedidoRequestDTO request =
                new AdicionarProdutoPedidoRequestDTO(99L, 2L, 3);

        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> pedidoService.adicionarProdutoAoPedido(request));
        verify(itemPedidoRepository, never()).save(any());
    }

    @Test
    void findAll_Success() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");
        Vendedor vendedor = new Vendedor();
        vendedor.setId(2L);

        Pedido pedido = new Pedido(cliente, vendedor, LocalDate.now(),
                FormaPagamento.PIX, StatusPedido.LIBERADO);
        pedido.setId(10L);

        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<PedidoResponseDTO> response = pedidoService.findAll();

        assertEquals(1, response.size());
        assertEquals(10L, response.get(0).id());
    }

    @Test
    void findById_Success() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");
        Vendedor vendedor = new Vendedor();
        vendedor.setId(2L);

        Pedido pedido = new Pedido(cliente, vendedor, LocalDate.now(),
                FormaPagamento.PIX, StatusPedido.LIBERADO);
        pedido.setId(10L);

        when(pedidoRepository.findById(10L)).thenReturn(Optional.of(pedido));

        PedidoResponseDTO response = pedidoService.findById(10L);

        assertEquals(10L, response.id());
        assertEquals("Maria", response.cliente().nome());
        assertEquals(StatusPedido.LIBERADO, response.statusPedido());
    }

    @Test
    void findById_NotFound() {
        when(pedidoRepository.findById(999L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> pedidoService.findById(999L));
        assertEquals("Pedido não encontrado!", ex.getMessage());
    }

    @Test
    void deletePedido_Success() {
        when(pedidoRepository.existsById(5L)).thenReturn(true);

        pedidoService.deletePedido(5L);

        verify(pedidoRepository).deleteById(5L);
    }

    @Test
    void deletePedido_NotFound() {
        when(pedidoRepository.existsById(5L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> pedidoService.deletePedido(5L));
        verify(pedidoRepository, never()).deleteById(anyLong());
    }
}
