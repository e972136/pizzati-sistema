package com.gaspar.pizzati.repository;

import com.gaspar.pizzati.entity.Vendedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface VendedorRepository extends JpaRepository<Vendedor,Long> {


    @Transactional
    @Query(value = "CALL public.almacenar_vendedor(:nombre_in,:departamento_in,:rv_id_vendedor);",nativeQuery = true)
    int addVendedor(String nombre_in, String departamento_in,Integer rv_id_vendedor);

    Page<Vendedor> findAllByActivo(boolean activo, Pageable page);

    Page<Vendedor> findAllByNombreContainingIgnoreCaseAndActivo(String busqueda, boolean b, Pageable page);

//    List<Vendedor> findAllByActivo(Boolean activo);
}
