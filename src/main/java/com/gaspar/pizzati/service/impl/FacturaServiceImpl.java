package com.gaspar.pizzati.service.impl;

import com.gaspar.pizzati.entity.Factura;
import com.gaspar.pizzati.helper.InvoiceNotFoundException;
import com.gaspar.pizzati.repository.FacturaRepository;
import com.gaspar.pizzati.service.IFacturaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements IFacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaServiceImpl(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public Factura getFactura(Long idFactura) {
        Optional<Factura> byId = facturaRepository.findById(idFactura);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new InvoiceNotFoundException("Factura "+idFactura+" no existe");
    }

    @Override
    public List<Factura> getFacturasCliente(Long idCliente) {
        List<Factura> byidCliente = facturaRepository.findByidCliente(idCliente);
        if(byidCliente.isEmpty()){
            throw  new InvoiceNotFoundException("Cliente "+idCliente+" sin facturas");
        }
        return byidCliente;
    }
}
