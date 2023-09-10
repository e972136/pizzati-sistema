package com.gaspar.pizzati.repository;

import com.gaspar.pizzati.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
