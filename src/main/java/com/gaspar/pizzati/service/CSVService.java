package com.gaspar.pizzati.service;

import com.gaspar.pizzati.entity.Cliente;
import com.gaspar.pizzati.entity.Factura;
import com.gaspar.pizzati.entity.FacturaDetalle;
import com.gaspar.pizzati.helper.CSVHelper;
import com.gaspar.pizzati.repository.ClienteRepository;
import com.gaspar.pizzati.repository.FacturaDetalleRepository;
import com.gaspar.pizzati.repository.FacturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    private final ClienteRepository clienteRepository;
    private final FacturaRepository facturaRepository;

    private final FacturaDetalleRepository facturaDetalleRepository;

    public CSVService(ClienteRepository clienteRepository, FacturaRepository facturaRepository, FacturaDetalleRepository facturaDetalleRepository) {
        this.clienteRepository = clienteRepository;
        this.facturaRepository = facturaRepository;
        this.facturaDetalleRepository = facturaDetalleRepository;
    }

    public void saveCliente(MultipartFile file){
        int i=0;
        try {
            int batchSize = 1000;
            List<Cliente> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
            int totalElementos = tutorials.size();
            for(i=0;i<totalElementos;i+=batchSize){
                if(i+batchSize>totalElementos){
                    List<Cliente> subiendo = tutorials.subList(i,totalElementos);
                    this.clienteRepository.saveAll(subiendo);
                    System.out.println("guardado hasta "+i+" "+totalElementos);
                    break;
                }
                List<Cliente> subiendo = tutorials.subList(i,i+batchSize);
                this.clienteRepository.saveAll(subiendo);
                System.out.println("guardado hasta "+i+" "+(i+batchSize));
            }

        } catch (IOException e) {
            System.out.println(i);
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void saveFactura(MultipartFile file) {
        int i=0;
        try {
            int batchSize = 1000;
            List<Factura> tutorials = CSVHelper.csvToFactura(file.getInputStream());
            int totalElementos = tutorials.size();
            for(i=0;i<totalElementos;i+=batchSize){
                if(i+batchSize>totalElementos){
                    List<Factura> subiendo = tutorials.subList(i,totalElementos);
                    facturaRepository.saveAll(subiendo);
                    System.out.println("guardado hasta "+i+" "+totalElementos);
                    break;
                }
                List<Factura> subiendo = tutorials.subList(i,i+batchSize);
                facturaRepository.saveAll(subiendo);
                System.out.println("guardado hasta "+i+" "+(i+batchSize));
            }

        } catch (IOException e) {
            System.out.println(i);
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void saveFacturaDetalle(MultipartFile file) {
        int i=0;
        try {
            int batchSize = 1000;
            List<FacturaDetalle> tutorials = CSVHelper.csvToFacturaDetalle(file.getInputStream());
            int totalElementos = tutorials.size();
            for(i=0;i<totalElementos;i+=batchSize){
                if(i+batchSize>totalElementos){
                    List<FacturaDetalle> subiendo = tutorials.subList(i,totalElementos);
                    facturaDetalleRepository.saveAll(subiendo);
                    System.out.println("guardado hasta "+i+" "+totalElementos);
                    break;
                }
                List<FacturaDetalle> subiendo = tutorials.subList(i,i+batchSize);
                facturaDetalleRepository.saveAll(subiendo);
                System.out.println("guardado hasta "+i+" "+(i+batchSize));
            }

        } catch (IOException e) {
            System.out.println(i);
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
