package com.gaspar.pizzati.repository;

import com.gaspar.pizzati.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import javax.transaction.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    /*
    Esta forma no funciona en postgresql
    @Transactional
    @Procedure(value   = "public.actualizar_direccion")
    void actualizar_direccion(
            @Param("id_cliente_in") Long id_cliente_in,
            @Param("direccion_cliente_in")String direccion_cliente_in);
*/
    @Transactional
    @Modifying(clearAutomatically = true)
//    los de arriba son necesarios si actualiza valores
    @Query(value = "CALL public.actualizar_direccion(:id_cliente_in,:direccion_cliente_in);",nativeQuery = true)
    void actualizar_direccion(
            Long id_cliente_in,
            String direccion_cliente_in);

}
