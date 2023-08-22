package com.gaspar.pizzati.service;

import com.gaspar.pizzati.entity.Vendedor;

import java.util.List;

public interface VendedorService {
    int saveVendedor(String nombre, String departamento);
    Vendedor getVendedor(Long idVendedor);

    List<Vendedor> getAllVendedor();
}
