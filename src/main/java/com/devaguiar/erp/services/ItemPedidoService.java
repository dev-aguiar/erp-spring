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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public ItemPedidoResponseDTO createItemPedido(Long pedidoId, ItemPedidoRequestDTO data) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));

        Produto produto = produtoRepository.findByIdForUpdate(data.produtoId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        produto.darBaixaNoEstoque(data.quantidade());

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(data.quantidade());
        itemPedido.setValorUnitario(produto.getPreco());

        itemPedidoRepository.save(itemPedido);
        produtoRepository.save(produto);

        return new ItemPedidoResponseDTO(itemPedido);
    }

    public List<ItemPedidoResponseDTO> getAllItemsByPedido(Long pedidoId) {
        List<ItemPedido> items = itemPedidoRepository.findByPedidoId(pedidoId);
        return items.stream().map(ItemPedidoResponseDTO::new).collect(Collectors.toList());
    }
}
