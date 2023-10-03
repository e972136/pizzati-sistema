package com.gaspar.pizzati.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaspar.pizzati.entity.Cliente;
import com.gaspar.pizzati.model.ClienteDto;
import com.gaspar.pizzati.model.LoggerColored;
import com.gaspar.pizzati.repository.ClienteRepository;
import com.gaspar.pizzati.service.ClienteService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final LoggerColored log = new LoggerColored(LoggerFactory.getLogger(getClass()));
    private final ObjectMapper objectMapper;

    private final ClienteRepository repository;

    public ClienteServiceImpl(ObjectMapper objectMapper, ClienteRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    @Override
    public void actualizardireccionPS(Long idcliente, String direccionNueva) {
        repository.actualizar_direccion(idcliente,direccionNueva);
    }

    @Override
    public Cliente getCliente(Long idCliente) {
        return repository.findById(idCliente).get();
    }

    @Override
    public Cliente saveNewCliente(ClienteDto clienteDto) {
        Cliente cliente = null;
        try {
            cliente = objectMapper.readValue(objectMapper.writeValueAsString(clienteDto), Cliente.class);
        } catch (JsonProcessingException e) {
            log.info(e+"");
            return null;
        }
        return repository.save(cliente);
    }

    @Override
    public String saveClienteByEntity(Cliente cliente) {
        System.err.println("antes: "+cliente);
        Cliente save = repository.save(cliente);
        System.err.println("despues: "+cliente);
        return "Cliente con id "+save.getIdCliente()+" se actualizo!";
    }
}
