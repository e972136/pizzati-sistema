package com.gaspar.pizzati.service;

import com.gaspar.pizzati.entity.Cliente;
import com.gaspar.pizzati.model.ClienteDto;

public interface ClienteService {
    void actualizardireccionPS(Long idcliente, String direccionNueva);

    Cliente getCliente(Long idCliente);

    Cliente saveNewCliente(ClienteDto cliente);

    String saveClienteByEntity(Cliente cliente);
}
