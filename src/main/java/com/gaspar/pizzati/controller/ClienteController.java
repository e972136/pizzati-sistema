package com.gaspar.pizzati.controller;

import com.gaspar.pizzati.entity.Cliente;
import com.gaspar.pizzati.model.ClienteDto;
import com.gaspar.pizzati.repository.ClienteRepository;
import com.gaspar.pizzati.service.ClienteService;
import org.springframework.web.bind.annotation.*;

@RestController("ClienteControllerV1")
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PutMapping("/{idcliente}")
    public String getFactura(
            @PathVariable Long idcliente,
            @RequestParam String direccionNueva
    ){
        service.actualizar_direccion_PS(idcliente,direccionNueva);
        return "exito";
    }

    @GetMapping("/{idCliente}")
    public Cliente getCliente(
            @PathVariable Long idCliente
    ){
        return service.getCliente(idCliente);
    }

    @PostMapping
    public Cliente saveCliente(
            @RequestBody ClienteDto cliente
    ){
        return service.saveNewCliente(cliente);
    }
}
