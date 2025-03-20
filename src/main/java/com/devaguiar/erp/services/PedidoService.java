package com.devaguiar.erp.services;

import com.devaguiar.erp.dtos.requests.PedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.PedidoResponseDTO;
import com.devaguiar.erp.entities.Cliente;
import com.devaguiar.erp.entities.Pedido;
import com.devaguiar.erp.repositories.ClienteRepository;
import com.devaguiar.erp.repositories.PedidoRepository;
import com.devaguiar.erp.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;


    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, VendedorRepository vendedorRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
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
