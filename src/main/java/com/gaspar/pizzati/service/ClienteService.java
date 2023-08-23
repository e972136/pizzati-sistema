package com.gaspar.pizzati.service;

import com.gaspar.pizzati.entity.Cliente;
import com.gaspar.pizzati.model.ClienteDto;

public interface ClienteService {
    void actualizar_direccion_PS(Long idcliente, String direccionNueva);

    Cliente getCliente(Long idCliente);

    Cliente saveNewCliente(ClienteDto cliente);
}
