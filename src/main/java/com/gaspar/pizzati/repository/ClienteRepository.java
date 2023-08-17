package com.gaspar.pizzati.repository;

import com.gaspar.pizzati.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
