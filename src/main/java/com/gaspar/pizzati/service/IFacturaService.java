package com.gaspar.pizzati.service;

import com.gaspar.pizzati.entity.Factura;

import java.util.List;

public interface IFacturaService {
    Factura getFactura(Long idFactura);

    List<Factura> getFacturasCliente(Long idCliente);
}
