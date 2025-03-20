package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.VendedorRequestDTO;
import com.devaguiar.erp.dtos.responses.VendedorResponseDTO;
import com.devaguiar.erp.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public VendedorRequestDTO createVendedor(@RequestBody VendedorRequestDTO data) {
        return vendedorService.createVendedor(data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(value = "/{id}")
    public VendedorRequestDTO updateVendedor(@PathVariable Long id, @RequestBody VendedorRequestDTO data) {
        return vendedorService.updateVendedor(id, data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/{id}")
    public void deleteVendedor(@PathVariable Long id) {
        vendedorService.deleteVendedor(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<VendedorResponseDTO> findAll() {
        return vendedorService.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{id}")
    public VendedorResponseDTO findById(@PathVariable Long id) {
        return vendedorService.findById(id);
    }
}
