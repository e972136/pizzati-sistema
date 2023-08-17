package com.gaspar.pizzati.repository;

import com.gaspar.pizzati.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository  extends JpaRepository<Factura,Long> {
}
