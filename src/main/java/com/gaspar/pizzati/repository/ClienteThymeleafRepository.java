package com.gaspar.pizzati.repository;

import com.gaspar.pizzati.entity.dataview.V_clientes_saldos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteThymeleafRepository extends JpaRepository<V_clientes_saldos,Long> {
    Page<V_clientes_saldos> findAllByNombreContainingIgnoreCase(String busqueda, Pageable page);
}
