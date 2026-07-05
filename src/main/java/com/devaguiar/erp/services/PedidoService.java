package com.devaguiar.erp.services;

import com.devaguiar.erp.dtos.requests.AdicionarProdutoPedidoRequestDTO;
import com.devaguiar.erp.dtos.requests.PedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.ClienteResumidoDTO;
import com.devaguiar.erp.dtos.responses.PedidoResponseDTO;
import com.devaguiar.erp.entities.*;
import com.devaguiar.erp.exceptions.ResourceNotFoundException;
import com.devaguiar.erp.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    private final ItemPedidoRepository itemPedidoRepository;


    public PedidoService(PedidoRepository pedidoRepository,
                         ProdutoRepository produtoRepository,
                         ClienteRepository clienteRepository,
                         VendedorRepository vendedorRepository,
                         ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public PedidoResponseDTO createPedido(PedidoRequestDTO data) {
        Cliente cliente = clienteRepository.findById(data.clienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));

        Vendedor vendedor = vendedorRepository.findById(data.vendedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor não encontrado!"));

        Pedido pedido = new Pedido(cliente, vendedor, data.dataPedido(),
                data.formaPagamento(), data.statusPedido());
        pedidoRepository.save(pedido);
        return new PedidoResponseDTO(pedido);
    }

    public PedidoResponseDTO updatePedido(Long id, PedidoRequestDTO data) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado!"));
        pedido.setDataPedido(data.dataPedido());
        pedido.setStatusPedido(data.statusPedido());
        pedidoRepository.save(pedido);
        return new PedidoResponseDTO(pedido);
    }

    public void adicionarProdutoAoPedido(AdicionarProdutoPedidoRequestDTO data) {
        Pedido pedido = pedidoRepository.findById(data.pedidoId())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        Produto produto = produtoRepository.findById(data.produtoId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        ItemPedido itemPedido = new ItemPedido(pedido, produto, data.quantidade());
        itemPedidoRepository.save(itemPedido);
    }

    public void deletePedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido não encontrado!");
        }
        pedidoRepository.deleteById(id);
    }

    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.findAll().stream()
                .map(PedidoResponseDTO::new).toList();
    }

    public PedidoResponseDTO findById(Long id) {
        Pedido result = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado!"));
        return new PedidoResponseDTO(result);
    }
}
