package com.gaspar.pizzati.controller;

import com.gaspar.pizzati.entity.Vendedor;
import com.gaspar.pizzati.service.VendedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendedor")
public class VendedorController {
    private final VendedorService service;

    public VendedorController(VendedorService service) {
        this.service = service;
    }

    @PostMapping
    public String saveVendedor(
            @RequestParam String nombreIn,
            @RequestParam String departamentoIn
    ){
        return service.saveVendedor(nombreIn,departamentoIn);
    }

    @GetMapping("/lista")
    public List<Vendedor> getVendedores(){
        return service.getAllVendedor();
    }

    @PatchMapping("/{idVendedor}")
    public Vendedor updateVendedor(
            @PathVariable Long idVendedor,
            @RequestParam(required = false) String nombreIn,
            @RequestParam(required = false) String departamentoIn
    ){
        return service.actualizarVendedor(idVendedor,nombreIn,departamentoIn);
    }
}
