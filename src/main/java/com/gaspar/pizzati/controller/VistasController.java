package com.gaspar.pizzati.controller;

import com.gaspar.pizzati.entity.dataview.ClienteFacturasConDetallefacturas;
import com.gaspar.pizzati.repository.ClienteFacturaConDetalleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vistas")
public class VistasController {
    private final ClienteFacturaConDetalleRepository repository;

    public VistasController(ClienteFacturaConDetalleRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/clientefacturadetalle")
    public List<ClienteFacturasConDetallefacturas> getFactura(){
        List<ClienteFacturasConDetallefacturas> all = repository.findAll();
        System.out.println("size:"+all.size());
        System.out.println("primero:"+all.get(0));
        return all;
    }
}
