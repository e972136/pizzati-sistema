package com.gaspar.pizzati.model;

import lombok.Data;

@Data
public class ClienteDto {
    String nombre;
    char naturalezaCliente;
    String direccion;
    String departamento;
    String ciudad;
    Long IdVendedor;

}
