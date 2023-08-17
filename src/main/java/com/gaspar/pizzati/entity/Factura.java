package com.gaspar.pizzati.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "factura")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Factura {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    Long idFactura;

    Long numeroFactura;

    Long idCliente;

    Long idVendedor;

    LocalDate fechaFactura;

    Double subTotal;

    Double impuesto;

    Double descuento;

    Double totalFactura;


}
