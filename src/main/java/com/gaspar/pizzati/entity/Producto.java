package com.gaspar.pizzati.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "codigo_a")
    String codigoA;

    String descripcion;
    Integer existencia;

    BigDecimal precioCompra;

    @Column(name = "precio_venta")
    BigDecimal precioVenta;
    @Column(name = "precio_venta_1")
    BigDecimal precioVenta1;

    @Column(name = "precio_venta_2")
    BigDecimal precioVenta2;

    @Column(name = "precio_venta_3")
    BigDecimal precioVenta3;

    String marca;
    String familia;
    String categoria;
    Boolean activo;
}
