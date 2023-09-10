package com.gaspar.pizzati.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Programador {
    private String nombre;
    private List<String> habilidades;
}
