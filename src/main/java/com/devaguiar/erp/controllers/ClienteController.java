package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.ClienteRequestDTO;
import com.devaguiar.erp.dtos.responses.ClienteResponseDTO;
import com.devaguiar.erp.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ClienteRequestDTO createCliente(@RequestBody ClienteRequestDTO data) {
        return clienteService.createCliente(data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(value = "/{id}")
    public ClienteRequestDTO updateCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO data) {
        return clienteService.updateCliente(id, data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ClienteResponseDTO> findAll() {
        return clienteService.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{id}")
    public ClienteResponseDTO findById(@PathVariable Long id) {
        return clienteService.findById(id);
    }
}
