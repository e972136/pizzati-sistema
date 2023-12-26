package com.gaspar.pizzati.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.util.Objects.isNull;

public record DetalleSolicitud(Integer id, String codigoA, String descripcion, String precio1, String precio2, String precio3, String precio4, String eleccion, Integer cantidad) {
    public BigDecimal total(){
        if(isNull(eleccion)){
            return BigDecimal.ZERO.setScale(2);
        }
        BigDecimal bigDecimal = new BigDecimal(eleccion);
        return bigDecimal.multiply(BigDecimal.valueOf(cantidad)).setScale(2, RoundingMode.CEILING);
    }
}
