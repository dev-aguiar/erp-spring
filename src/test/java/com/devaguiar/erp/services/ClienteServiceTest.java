package com.devaguiar.erp.services;

import com.devaguiar.erp.dtos.requests.ClienteRequestDTO;
import com.devaguiar.erp.dtos.responses.ClienteResponseDTO;
import com.devaguiar.erp.entities.Cliente;
import com.devaguiar.erp.exceptions.ResourceNotFoundException;
import com.devaguiar.erp.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void createCliente_Success() {
        ClienteRequestDTO request = new ClienteRequestDTO(
                "Maria",
                "maria@email.com",
                "11999999999",
                "Rua A");

        ClienteResponseDTO response = clienteService.createCliente(request);

        assertNotNull(response);
        assertEquals("Maria", response.nome());
        assertEquals("maria@email.com", response.email());
        assertEquals("11999999999", response.telefone());
        assertEquals("Rua A", response.endereco());
    }

    @Test
    void updateCliente_Success() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");
        cliente.setEmail("maria@email.com");
        cliente.setTelefone("11999999999");
        cliente.setEndereco("Rua A");

        ClienteRequestDTO request = new ClienteRequestDTO(
                "João",
                "joao@email.com",
                "11988888888",
                "Rua B");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        ClienteResponseDTO response = clienteService.updateCliente(1L, request);

        assertEquals("João", response.nome());
        assertEquals("joao@email.com", response.email());
        assertEquals("11988888888", response.telefone());
        assertEquals("Rua B", response.endereco());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void updateCliente_NotFound() {
        ClienteRequestDTO request = new ClienteRequestDTO(
                "João",
                "joao@email.com",
                "11988888888",
                "Rua B");

        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> clienteService.updateCliente(99L, request));
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void findAll_Success() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");

        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<ClienteResponseDTO> response = clienteService.findAll();

        assertEquals(1, response.size());
        assertEquals("Maria", response.get(0).nome());
    }

    @Test
    void findById_Success() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");
        cliente.setEmail("maria@email.com");
        cliente.setTelefone("11999999999");
        cliente.setEndereco("Rua A");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        ClienteResponseDTO response = clienteService.findById(1L);

        assertEquals(1L, response.id());
    }

    @Test
    void findById_NotFound() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> clienteService.findById(1L));

        assertEquals("Cliente não encontrado!", ex.getMessage());
    }

    @Test
    void deleteCliente_Success() {
        when(clienteRepository.existsById(1L)).thenReturn(true);

        clienteService.deleteCliente(1L);

        verify(clienteRepository).deleteById(1L);
    }

    @Test
    void deleteCliente_NotFound() {
        when(clienteRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> clienteService.deleteCliente(99L));

        verify(clienteRepository, never()).deleteById(99L);
    }
}


