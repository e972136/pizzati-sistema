package com.gaspar.pizzati.service.impl;

import com.gaspar.pizzati.entity.Vendedor;
import com.gaspar.pizzati.repository.VendedorRepository;
import com.gaspar.pizzati.service.VendedorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository repository;

    public VendedorServiceImpl(VendedorRepository repository) {
        this.repository = repository;
    }

    @Override
    public String saveVendedor(String nombre, String departamento) {
        if(isNull(nombre) || nombre.length()<2){
            return "Falta nombre";
        }
        if(isNull(departamento) || departamento.length()<2){
            return "Falta departamento";
        }
        int id = 1;
        try {
            id= repository.addVendedor(nombre,departamento,id);
        }
        catch (Exception x){
            return "Registro no se pudo almacenar, ver log ";
        }
        return "Registro con id "+id+" fue salvado con exito";
    }

    @Override
    public String saveVendedor(Vendedor vendedor) {
        Vendedor save = repository.save(vendedor);
        return "Registro con id "+save.getIdVendedor()+" fue salvado con exito";
    }
    @Override
    public Vendedor getVendedor(Long idVendedor) {
        return repository.findById(idVendedor).get();
    }

    @Override
    public Page<Vendedor> getAllVendedor(Pageable page) {
//        return repository.findAll(Sort.by(Sort.Direction.ASC,"nombre"));
//        return repository.findAllByActivo(true,Sort.by(Sort.Direction.ASC,"nombre"),page);  ver como poner sort en page
        return repository.findAllByActivo(true,page);
    }
    @Override
    public List<Vendedor> getAllVendedor() {
        return repository.findAll();
    }

    @Override
    public Page<Vendedor> getAllVendedorFiltrado(String busqueda, Pageable page) {
        return repository.findAllByNombreContainingIgnoreCaseAndActivo(busqueda,true,page);
    }

    @Override
    public Vendedor actualizarVendedor(Long idVendedor, String nombreIn, String departamentoIn) {
        Vendedor vendedor = getVendedor(idVendedor);
        if(nonNull(nombreIn)){
            vendedor.setNombre(nombreIn);
        }
        if(nonNull(departamentoIn)){
            vendedor.setDepartamento(departamentoIn);
        }
        return repository.save(vendedor);
    }

    @Override
    public void deleteVendedorById(Long id) {
        Vendedor vendedor = getVendedor(id);
        vendedor.setActivo(Boolean.FALSE);
//        repository.deleteById(id);
        repository.save(vendedor);
    }




}
