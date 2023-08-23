package com.gaspar.pizzati.controller.thymeleaf;

import com.gaspar.pizzati.entity.Cliente;
import com.gaspar.pizzati.entity.Vendedor;
import com.gaspar.pizzati.entity.dataview.V_clientes_saldos;
import com.gaspar.pizzati.repository.ClienteRepository;
import com.gaspar.pizzati.repository.ClienteThymeleafRepository;
import com.gaspar.pizzati.repository.VendedorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/thymeleaf/cliente")
public class ClienteThymeleafController {
    private final ClienteThymeleafRepository repository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;

    public ClienteThymeleafController(ClienteThymeleafRepository repository, ClienteRepository clienteRepository, VendedorRepository vendedorRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
    }


    @GetMapping("/obtenerTodosVClientes")
    public String obtenerTodosClientesVista(
            @PageableDefault(size = 5,sort = "nombre",direction = Sort.Direction.ASC) Pageable page,
            @RequestParam(value = "message",required = false) String message,
            @RequestParam(required = false) String busqueda,
            Model model
    ){
        System.err.println("/thymeleaf/cliente/obtenerTodosVClientes");
        Page<V_clientes_saldos> clientes;
        if(isNull(busqueda)){
            clientes = repository.findAll(page);
        }else{
            clientes = repository.findAllByNombreContainingIgnoreCase(busqueda,page);
        }
        model.addAttribute("list",clientes);
        model.addAttribute("message",message);
        return "cliente/clienteListado";
    }

    @GetMapping("/agregarCliente")
    public String showRegistration(
            Model model
    ){
        System.err.println("/thymeleaf/cliente/agregarCliente");
        List<Vendedor> vendedores = vendedorRepository.findAllByActivo(true);
        model.addAttribute("vendedores",vendedores);
        return "cliente/clienteAgregar";
    }

    @PostMapping("/save")
    public String saveCliente(
            @ModelAttribute Cliente cliente,
            Model model
    ){
        System.err.println("/thymeleaf/cliente/save");
        Cliente saved = clienteRepository.save(cliente);
        model.addAttribute("message","Se salvo con id "+saved.getIdCliente());
        return "cliente/clienteAgregar";
    }

}
