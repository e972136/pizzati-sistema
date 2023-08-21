package com.gaspar.pizzati.entity.dataview;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table(name="cliente_facturas_con_detallefacturas")
@Data
//@Data si quitas data, solo seria para trabajo interno
@Immutable
@ToString
public class ClienteFacturasConDetallefacturas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    String id;

    @Column(name = "id_cliente")
    Long idCliente;

    @Column
    Long idVendedor;

    @Column
    Double totalFactura;

    @Column
    Double totalFacturaDetalle;
}
