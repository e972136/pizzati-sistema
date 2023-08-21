package com.gaspar.pizzati.repository;

import com.gaspar.pizzati.entity.dataview.ClienteFacturasConDetallefacturas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteFacturaConDetalleRepository extends JpaRepository<ClienteFacturasConDetallefacturas,String> {
}
