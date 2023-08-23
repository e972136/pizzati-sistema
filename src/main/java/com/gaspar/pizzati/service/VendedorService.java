package com.gaspar.pizzati.service;

import com.gaspar.pizzati.entity.Vendedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendedorService {
    String saveVendedor(String nombre, String departamento);

    String saveVendedor(Vendedor vendedor);

    Vendedor getVendedor(Long idVendedor);

    Page<Vendedor> getAllVendedor(Pageable page);

    Vendedor actualizarVendedor(Long idVendedor, String nombreIn, String departamentoIn);

    void deleteVendedorById(Long id);

    List<Vendedor> getAllVendedor();

    Page<Vendedor> getAllVendedorFiltrado(String busqueda, Pageable page);
}
