package com.gaspar.pizzati.repository;

import com.gaspar.pizzati.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaRepository  extends JpaRepository<Factura,Long> {
    List<Factura> findByidCliente(Long idCliente);
}
