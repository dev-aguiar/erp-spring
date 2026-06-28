package com.devaguiar.erp.services;

import com.devaguiar.erp.dtos.requests.VendedorRequestDTO;
import com.devaguiar.erp.dtos.responses.VendedorResponseDTO;
import com.devaguiar.erp.entities.Vendedor;
import com.devaguiar.erp.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VendedorService {

    private final VendedorRepository repository;

    @Autowired
    public VendedorService(VendedorRepository repository) {
        this.repository = repository;
    }

    public VendedorResponseDTO createVendedor(VendedorRequestDTO data) {
        Vendedor vendedor = new Vendedor(data);
        repository.save(vendedor);
        return new VendedorResponseDTO(vendedor);
    }

    public VendedorResponseDTO updateVendedor(Long id, VendedorRequestDTO data) {
        Vendedor vendedor = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendedor não encontrado!"));

        vendedor.setNome(data.nome());
        vendedor.setDataNascimento(data.dataNascimento());
        repository.save(vendedor);
        return new VendedorResponseDTO(vendedor);
    }

    public void deleteVendedor(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendedor não encontrado!");
        }
        repository.deleteById(id);
    }

    public List<VendedorResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(VendedorResponseDTO::new)
                .toList();
    }

    public VendedorResponseDTO findById(Long id) {
        Vendedor result = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendedor não encontrado!"));
        return new VendedorResponseDTO(result);
    }
}
