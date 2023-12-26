package com.gaspar.pizzati.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaspar.pizzati.entity.Producto;
import com.gaspar.pizzati.model.LoggerColored;
import com.gaspar.pizzati.model.ProductoDto;
import com.gaspar.pizzati.repository.ProductoRepository;
import com.gaspar.pizzati.service.ProductoService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final LoggerColored log = new LoggerColored(LoggerFactory.getLogger(getClass()));
    private final ProductoRepository repository;
    private final ObjectMapper objectMapper;

    public ProductoServiceImpl(ProductoRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Producto> allProductos() {
        return repository.findAll();
    }

    @Override
    public Producto getProducto(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Producto saveProductoNuevo(ProductoDto productoDto) {
        Producto producto = null;
        try {
            producto = objectMapper.readValue(objectMapper.writeValueAsString(productoDto), Producto.class);
        } catch (JsonProcessingException e) {
            log.infoB(e+"");
            return null;
        }

        return producto;
    }

    @Override
    public Producto findByCodigoA(String codigo) {
        return repository.findByCodigoA(codigo).orElse(null);
    }

}
