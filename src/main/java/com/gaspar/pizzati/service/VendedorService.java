package com.gaspar.pizzati.service;

import com.gaspar.pizzati.entity.Vendedor;

import java.util.List;

public interface VendedorService {
    String saveVendedor(String nombre, String departamento);

    String saveVendedor(Vendedor vendedor);

    Vendedor getVendedor(Long idVendedor);

    List<Vendedor> getAllVendedor();

    Vendedor actualizarVendedor(Long idVendedor, String nombreIn, String departamentoIn);

    void deleteVendedorById(Long id);
}
