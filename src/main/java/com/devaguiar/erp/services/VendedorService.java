package com.devaguiar.erp.services;

import com.devaguiar.erp.dtos.requests.VendedorRequestDTO;
import com.devaguiar.erp.dtos.responses.VendedorResponseDTO;
import com.devaguiar.erp.entities.Vendedor;
import com.devaguiar.erp.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {

    private final VendedorRepository repository;

    @Autowired
    public VendedorService(VendedorRepository repository) {
        this.repository = repository;
    }

    public VendedorRequestDTO createVendedor(VendedorRequestDTO data) {
        Vendedor vendedor = new Vendedor(data);
        repository.save(vendedor);
        return data;
    }

    public VendedorRequestDTO updateVendedor(Long id, VendedorRequestDTO data) {
        Vendedor vendedor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado!"));

        vendedor.setNome(data.nome());
        vendedor.setDataNascimento(data.dataNascimento());
        repository.save(vendedor);
        return data;
    }

    public void deleteVendedor(Long id) {
        repository.deleteById(id);
    }

    public List<VendedorResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(VendedorResponseDTO::new)
                .toList();
    }

    public VendedorResponseDTO findById(Long id) {
        Vendedor result = repository.findById(id).get();
        return new VendedorResponseDTO(result);
    }
}
