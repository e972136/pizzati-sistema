package com.gaspar.pizzati.controller.thymeleaf;

import com.gaspar.pizzati.entity.Cliente;
import com.gaspar.pizzati.entity.Vendedor;
import com.gaspar.pizzati.entity.dataview.VClientesSaldos;
import com.gaspar.pizzati.model.ClienteDto;
import com.gaspar.pizzati.model.LoggerColored;
import com.gaspar.pizzati.repository.ClienteThymeleafRepository;
import com.gaspar.pizzati.repository.VendedorRepository;
import com.gaspar.pizzati.service.ClienteService;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/thymeleaf/cliente")
@CrossOrigin(origins = "*")
public class ClienteThymeleafController {
    private final LoggerColored log = new LoggerColored(LoggerFactory.getLogger(getClass()));

    private final ClienteThymeleafRepository repository;
    private final ClienteService clienteService;
    private final VendedorRepository vendedorRepository;

    public ClienteThymeleafController(ClienteThymeleafRepository repository, ClienteService clienteService, VendedorRepository vendedorRepository) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.vendedorRepository = vendedorRepository;
    }


    @GetMapping("/obtenerTodosVClientes")
    public String obtenerTodosClientesVista(
            @PageableDefault(size = 5,sort = "nombre",direction = Sort.Direction.ASC) Pageable page,
            @RequestParam(value = "message",required = false) String message,
            @RequestParam(required = false) String busqueda,
            Model model
    ){
        log.info("/thymeleaf/cliente/obtenerTodosVClientes");
        Page<VClientesSaldos> clientes;
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
        log.info("/thymeleaf/cliente/agregarCliente");
        List<Vendedor> vendedores = vendedorRepository.findAllByActivo(true);
        model.addAttribute("vendedores",vendedores);
        return "cliente/clienteAgregar";
    }

    @PostMapping("/save")
    public String saveCliente(
            @ModelAttribute ClienteDto clienteDto,
            Model model
    ){
        log.info("/thymeleaf/cliente/save");
        Cliente saved =  clienteService.saveNewCliente(clienteDto);
        model.addAttribute("message","Se salvo con id "+saved.getIdCliente());
        return "cliente/clienteAgregar";
    }


    @GetMapping("/editarCliente")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam Long id
    ){
        log.info("/thymeleaf/cliente/editarCliente");
        String page = null;
        try{
            Cliente cliente = clienteService.getCliente(id);
            log.info(""+cliente);
            model.addAttribute("cliente",cliente);
            List<Vendedor> vendedores = vendedorRepository.findAllByActivo(true);
            model.addAttribute("vendedores",vendedores);
            page = "cliente/clienteEditar";
        }catch (Exception e){
            e.printStackTrace();
            attributes.addAttribute("message",e.getMessage());
            page = "redirect:obtenerTodosVClientes";
        }
        return page;
    }

    @PostMapping("/update")
    public String updateVendedor(
            @ModelAttribute Cliente cliente,
            RedirectAttributes attributes
    ){
        log.info("/thymeleaf/cliente/update");
        log.info(""+cliente);
        String s = clienteService.saveClienteByEntity(cliente);
        attributes.addAttribute("message", s);
        return "redirect:obtenerTodosVClientes";
    }

    /*
      @PostMapping("/update")
    public String updateVendedor(
            @ModelAttribute Vendedor vendedor,
            RedirectAttributes attributes
    ) {
        log.info("/thymeleaf/vendedor/update");
        String s = service.saveVendedor(vendedor);
        attributes.addAttribute("message", s);
        return "redirect:obtenerTodosVendedores";
    }
     */
}
