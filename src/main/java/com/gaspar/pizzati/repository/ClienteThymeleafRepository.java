package com.gaspar.pizzati.repository;

import com.gaspar.pizzati.entity.dataview.VClientesSaldos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteThymeleafRepository extends JpaRepository<VClientesSaldos,Long> {
    Page<VClientesSaldos> findAllByNombreContainingIgnoreCase(String busqueda, Pageable page);
}
