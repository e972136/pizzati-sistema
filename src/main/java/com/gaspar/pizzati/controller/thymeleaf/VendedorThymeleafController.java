package com.gaspar.pizzati.controller.thymeleaf;

import com.gaspar.pizzati.entity.Vendedor;
import com.gaspar.pizzati.helper.InvoiceNotFoundException;
import com.gaspar.pizzati.service.VendedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/thymeleaf/vendedor")
public class VendedorThymeleafController {
    private final VendedorService service;

    public VendedorThymeleafController(VendedorService service) {
        this.service = service;
    }

    @GetMapping("/principal")
    public String showHomePage(){
        System.err.println("/thymeleaf/vendedor/principal");
        return "vendedorHomePage";
    }

    @GetMapping("/obtenerTodosVendedores")
    public String obtenerTodosVendedores(
            @RequestParam(value = "message",required = false) String message,
            Model model
    ){
        System.err.println("/thymeleaf/vendedor/obtenerTodosVendedores");
        List<Vendedor> vendedores = service.getAllVendedor();
        model.addAttribute("list",vendedores);
        model.addAttribute("message",message);
        return "vendedor/vendedorListado";
    }

    @GetMapping("/agregarVendedor")
    public String showRegistration() {
        System.err.println("/thymeleaf/vendedor/agregarVendedor");
        return "vendedor/vendedorAgregar";
    }

    @GetMapping("/editarVendedor")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam Long id
    ) {
        System.err.println("/thymeleaf/vendedor/editarVendedor");
        String page = null;
        try {
            Vendedor vendedor = service.getVendedor(id);
            System.err.println("vendedor:"+vendedor);
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
        System.err.println("/thymeleaf/vendedor/save");
        String message = service.saveVendedor(vendedor.getNombre(), vendedor.getDepartamento());
        model.addAttribute("message",message);
        return "vendedor/vendedorAgregar";
    }

    @PostMapping("/update")
    public String updateVendedor(
            @ModelAttribute Vendedor vendedor,
            RedirectAttributes attributes
    ) {
        System.err.println("/thymeleaf/vendedor/update");
        String s = service.saveVendedor(vendedor);
        attributes.addAttribute("message", s);
        return "redirect:obtenerTodosVendedores";
    }

    @GetMapping("/delete")
    public String deleteVendedor(
            @RequestParam Long id,
            RedirectAttributes attributes
    ) {
        System.err.println("/thymeleaf/vendedor/delete");
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
