package com.devaguiar.erp.services;

import com.devaguiar.erp.dtos.requests.VendedorRequestDTO;
import com.devaguiar.erp.dtos.responses.VendedorResponseDTO;
import com.devaguiar.erp.entities.Vendedor;
import com.devaguiar.erp.exceptions.ResourceNotFoundException;
import com.devaguiar.erp.repositories.VendedorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VendedorServiceTest {

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private VendedorService vendedorService;

    @Test
    void createVendedor_Success() {
        VendedorRequestDTO request = new VendedorRequestDTO(
                "Carlos",
                LocalDate.of(1990, 5, 15));

        VendedorResponseDTO response = vendedorService.createVendedor(request);

        assertNotNull(response);
        assertEquals("Carlos", response.nome());
        assertEquals(LocalDate.of(1990, 5, 15), response.dataNascimento());
        verify(vendedorRepository).save(any(Vendedor.class));
    }

    @Test
    void updateVendedor_Success() {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(1L);
        vendedor.setNome("Carlos");
        vendedor.setDataNascimento(LocalDate.of(1990, 5, 15));

        VendedorRequestDTO request = new VendedorRequestDTO(
                "Carlos Silva",
                LocalDate.of(1991, 6, 20));

        when(vendedorRepository.findById(1L)).thenReturn(Optional.of(vendedor));

        VendedorResponseDTO response = vendedorService.updateVendedor(1L, request);

        assertEquals("Carlos Silva", response.nome());
        assertEquals(LocalDate.of(1991, 6, 20), response.dataNascimento());
        verify(vendedorRepository).save(vendedor);
    }

    @Test
    void updateVendedor_NotFound() {
        VendedorRequestDTO request = new VendedorRequestDTO(
                "Carlos Silva",
                LocalDate.of(1991, 6, 20));

        when(vendedorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> vendedorService.updateVendedor(99L, request));
        verify(vendedorRepository, never()).save(any());
    }

    @Test
    void findAll_Success() {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(1L);
        vendedor.setNome("Carlos");

        when(vendedorRepository.findAll()).thenReturn(List.of(vendedor));

        List<VendedorResponseDTO> response = vendedorService.findAll();

        assertEquals(1, response.size());
        assertEquals("Carlos", response.get(0).nome());
    }

    @Test
    void findById_Success() {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(1L);
        vendedor.setNome("Carlos");
        vendedor.setDataNascimento(LocalDate.of(1990, 5, 15));

        when(vendedorRepository.findById(1L)).thenReturn(Optional.of(vendedor));

        VendedorResponseDTO response = vendedorService.findById(1L);

        assertEquals(1L, response.id());
        assertEquals("Carlos", response.nome());
        assertEquals(LocalDate.of(1990, 5, 15), response.dataNascimento());
    }

    @Test
    void findById_NotFound() {
        when(vendedorRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> vendedorService.findById(1L));

        assertEquals("Vendedor não encontrado!", ex.getMessage());
    }

    @Test
    void deleteVendedor_Success() {
        when(vendedorRepository.existsById(1L)).thenReturn(true);

        vendedorService.deleteVendedor(1L);

        verify(vendedorRepository).deleteById(1L);
    }

    @Test
    void deleteVendedor_NotFound() {
        when(vendedorRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> vendedorService.deleteVendedor(1L));

        verify(vendedorRepository, never()).deleteById(anyLong());
    }
}
