package com.gaspar.pizzati.service;

import com.gaspar.pizzati.entity.Producto;
import com.gaspar.pizzati.model.ProductoDto;

import java.util.List;

public interface ProductoService {
    List<Producto> allProductos();
    Producto getProducto(Integer id);

    Producto saveProductoNuevo(ProductoDto productoDto);

    Producto findByCodigoA(String codigo);
}
