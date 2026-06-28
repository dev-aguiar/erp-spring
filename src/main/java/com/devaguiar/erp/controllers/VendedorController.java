package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.VendedorRequestDTO;
import com.devaguiar.erp.dtos.responses.VendedorResponseDTO;
import com.devaguiar.erp.services.VendedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    @Autowired
    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping
    public ResponseEntity<VendedorResponseDTO> createVendedor(@Valid @RequestBody VendedorRequestDTO data) {
        VendedorResponseDTO create = vendedorService.createVendedor(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VendedorResponseDTO> updateVendedor(@PathVariable Long id, @Valid @RequestBody VendedorRequestDTO data) {
        VendedorResponseDTO updated = vendedorService.updateVendedor(id, data);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable Long id) {
        vendedorService.deleteVendedor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<VendedorResponseDTO> findAll() {
        return vendedorService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendedorResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vendedorService.findById(id));
    }
}
