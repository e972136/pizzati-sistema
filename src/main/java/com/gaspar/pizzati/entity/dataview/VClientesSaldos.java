package com.gaspar.pizzati.entity.dataview;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table(name="v_clientes_saldos")
@Data
//@Data si quitas data, solo seria para trabajo interno
@Immutable
@ToString
public class VClientesSaldos {
    @Id
    @Column(name = "id_cliente", updatable = false, nullable = false)
    String idCliente;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "ciudad")
    String ciudad;

    @Column(name = "departamento")
    String departamento;

    @Column(name = "vendedor_acumulado")
    String vendedorAcumulado;

    @Column(name = "total")
    String total;

}
