package com.devaguiar.erp.services;

import com.devaguiar.erp.dtos.requests.ClienteRequestDTO;
import com.devaguiar.erp.dtos.responses.ClienteResponseDTO;
import com.devaguiar.erp.entities.Cliente;
import com.devaguiar.erp.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteRequestDTO createCliente(@RequestBody ClienteRequestDTO data) {
        Cliente cliente = new Cliente(data);
        clienteRepository.save(cliente);
        return data;
    }

    public ClienteRequestDTO updateCliente(Long id, ClienteRequestDTO data) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        cliente.setNome(data.nome());
        cliente.setEmail(data.email());
        cliente.setTelefone(data.telefone());
        cliente.setEndereco(data.endereco());
        clienteRepository.save(cliente);
        return data;
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public List<ClienteResponseDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(ClienteResponseDTO::new).toList();
    }

    public ClienteResponseDTO findById(Long id) {
        Cliente result = clienteRepository.findById(id).get();
        return new ClienteResponseDTO(result);
    }
}
