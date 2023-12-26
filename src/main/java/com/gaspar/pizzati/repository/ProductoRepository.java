package com.gaspar.pizzati.repository;

import com.gaspar.pizzati.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
    Optional<Producto> findByCodigoA(String codigo);
}
