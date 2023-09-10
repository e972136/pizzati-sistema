package com.gaspar.pizzati.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idProducto;

    @Column(name = "codigo_a")
    String codigoA;

    String descripcion;
    Integer existencia;
    Double precioCompra;
    Double precioVenta;
    String marca;
    String familia;
    String categoria;
    Boolean activo;
}
