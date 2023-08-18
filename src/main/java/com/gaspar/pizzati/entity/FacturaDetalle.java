package com.gaspar.pizzati.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "factura_detalle")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long idFacturaDetalle;

    Long idFactura;

    Long idProducto;

    Double cantidad;

    Double precioUnidad;

    Double precioVendido;

    Double precioCosto;

    Double impuestoInidad;

    Double subTotalUnidad;

    Double totalImpuesto;

    Double descuento;

    Double totalIndividual;


}
