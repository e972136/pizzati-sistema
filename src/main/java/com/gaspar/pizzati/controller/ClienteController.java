package com.gaspar.pizzati.controller;

import com.gaspar.pizzati.entity.Factura;
import com.gaspar.pizzati.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/{idcliente}")
    public String getFactura(
            @PathVariable Long idcliente,
            @RequestParam String direccionNueva
    ){
        clienteRepository.actualizar_direccion(idcliente,direccionNueva);
        return "exito";
    }
}
