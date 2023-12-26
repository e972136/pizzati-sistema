package com.gaspar.pizzati.controller;

import com.gaspar.pizzati.entity.Producto;
import com.gaspar.pizzati.model.LoggerColored;
import com.gaspar.pizzati.service.ProductoService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("ProductoControllerV1")
@RequestMapping("/api/producto")
public class ProductoController {
    private final LoggerColored log = new LoggerColored(LoggerFactory.getLogger(getClass()));
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    List<Producto> findAll(){
        return service.allProductos();
    }

    @GetMapping("/{id}")
    Producto getProducto(@PathVariable Integer id){
        return service.getProducto(id);
    }
}
