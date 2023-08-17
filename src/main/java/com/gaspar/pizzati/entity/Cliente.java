package com.gaspar.pizzati.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class Cliente {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    Long idCliente;

    char naturalezaCliente;

    String nombre;

    String direccion;

    String departamento;

    String ciudad;

    Long IdVendedor;
}