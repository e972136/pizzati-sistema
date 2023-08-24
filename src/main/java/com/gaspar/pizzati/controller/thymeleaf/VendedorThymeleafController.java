package com.gaspar.pizzati.controller.thymeleaf;

import com.gaspar.pizzati.entity.Vendedor;
import com.gaspar.pizzati.helper.InvoiceNotFoundException;
import com.gaspar.pizzati.model.LoggerColored;
import com.gaspar.pizzati.service.VendedorService;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Pageable;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/thymeleaf/vendedor")
@CrossOrigin(origins = "*")
public class VendedorThymeleafController {
    private final LoggerColored log = new LoggerColored(LoggerFactory.getLogger(getClass()));
    private final VendedorService service;

    public VendedorThymeleafController(VendedorService service) {
        this.service = service;
    }

    @GetMapping("/principal")
    public String showHomePage(){
        log.info("/thymeleaf/vendedor/principal");
        return "vendedorHomePage";
    }

    @GetMapping("/obtenerTodosVendedores")
    public String obtenerTodosVendedores(
            @PageableDefault(size = 5,sort = "nombre",direction = Sort.Direction.ASC) Pageable page,
            @RequestParam(value = "message",required = false) String message,
            @RequestParam(required = false) String busqueda,
            Model model
    ){
        log.info("/thymeleaf/vendedor/obtenerTodosVendedores");
        Page<Vendedor> vendedores;
        if(isNull(busqueda)){
            vendedores = service.getAllVendedor(page);
        }else{
            vendedores = service.getAllVendedorFiltrado(busqueda,page);
        }

        model.addAttribute("list",vendedores);
        model.addAttribute("message",message);
        return "vendedor/vendedorListado";
    }

    @GetMapping("/agregarVendedor")
    public String showRegistration() {
        log.info("/thymeleaf/vendedor/agregarVendedor");
        return "vendedor/vendedorAgregar";
    }

    @GetMapping("/editarVendedor")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam Long id
    ) {
        log.info("/thymeleaf/vendedor/editarVendedor");
        String page = null;
        try {
            Vendedor vendedor = service.getVendedor(id);
            log.info("vendedor:"+vendedor);
            model.addAttribute("vendedor", vendedor);
            page="vendedor/vendedorEditarPage";
        } catch (InvoiceNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page="redirect:obtenerTodosVendedores";
        }
        return page;
    }

    @PostMapping("/save")
    public String saveVendedor(
        @ModelAttribute Vendedor vendedor,
        Model model
    ){
        log.info("/thymeleaf/vendedor/save");
        String message = service.saveVendedor(vendedor.getNombre(), vendedor.getDepartamento());
        model.addAttribute("message",message);
        return "vendedor/vendedorAgregar";
    }

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

    @GetMapping("/delete")
    public String deleteVendedor(
            @RequestParam Long id,
            RedirectAttributes attributes
    ) {
        log.info("/thymeleaf/vendedor/delete");
        try {
            service.deleteVendedorById(id);
            attributes.addAttribute("message", "Vendedor con Id : '"+id+"' fue removido!");
        } catch (InvoiceNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:obtenerTodosVendedores";
    }
}
