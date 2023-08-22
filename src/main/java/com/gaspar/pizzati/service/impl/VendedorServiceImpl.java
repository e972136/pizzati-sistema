package com.gaspar.pizzati.service.impl;

import com.gaspar.pizzati.entity.Vendedor;
import com.gaspar.pizzati.repository.VendedorRepository;
import com.gaspar.pizzati.service.VendedorService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository repository;

    public VendedorServiceImpl(VendedorRepository repository) {
        this.repository = repository;
    }

    @Override
    public int saveVendedor(String nombre, String departamento) {
        Integer patito = 1;
        return repository.addVendedor(nombre,departamento,patito);
    }

    @Override
    public Vendedor getVendedor(Long idVendedor) {
        return null;
    }

    @Override
    public List<Vendedor> getAllVendedor() {
        return repository.findAll(Sort.by(Sort.Direction.ASC,"nombre"));
    }
}
