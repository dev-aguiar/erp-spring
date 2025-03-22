package com.devaguiar.erp.services;

import com.devaguiar.erp.dtos.requests.AdicionarProdutoPedidoRequestDTO;
import com.devaguiar.erp.dtos.requests.PedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.PedidoResponseDTO;
import com.devaguiar.erp.entities.Cliente;
import com.devaguiar.erp.entities.ItemPedido;
import com.devaguiar.erp.entities.Pedido;
import com.devaguiar.erp.entities.Produto;
import com.devaguiar.erp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    private final ItemPedidoRepository itemPedidoRepository;


    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository, VendedorRepository vendedorRepository, ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public PedidoRequestDTO createPedido(@RequestBody PedidoRequestDTO data) {
        Pedido pedido = new Pedido(data, clienteRepository, vendedorRepository);
        pedidoRepository.save(pedido);
        return data;
    }

    public PedidoRequestDTO updatePedido(Long id, PedidoRequestDTO data) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
        pedido.setDataPedido(data.dataPedido());
        pedido.setStatusPedido(data.statusPedido());
        pedidoRepository.save(pedido);
        return data;
    }

    public void adicionarProdutoAoPedido(AdicionarProdutoPedidoRequestDTO data) {
        Pedido pedido = pedidoRepository.findById(data.pedidoId())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        Produto produto = produtoRepository.findById(data.produtoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        ItemPedido itemPedido = new ItemPedido(pedido, produto, data.quantidade());
        itemPedidoRepository.save(itemPedido);
    }

    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.findAll().stream()
                .map(PedidoResponseDTO::new).toList();
    }

    public PedidoResponseDTO findById(Long id) {
        Pedido result = pedidoRepository.findById(id).get();
        return new PedidoResponseDTO(result);
    }
}
