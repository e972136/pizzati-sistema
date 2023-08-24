package com.gaspar.pizzati.controller;

import com.gaspar.pizzati.helper.CSVHelper;
import com.gaspar.pizzati.model.LoggerColored;
import com.gaspar.pizzati.service.CSVService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/csv")
public class CSVController {
    private final CSVService fileService;
    private final LoggerColored log = new LoggerColored(LoggerFactory.getLogger(getClass()));


    public CSVController(CSVService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(path="/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir archivo, seleccionar el archivo ejemplo (Consulta1.scv) cuenta con 11k registros")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.saveCliente(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                log.info(e+"");
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    @PostMapping(path="/upload/factura",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir archivo, seleccionar el archivo ejemplo (Consulta1.scv) cuenta con 11k registros")
    public ResponseEntity<String> uploadFileFactura(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.saveFactura(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                log.info(e+"");
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @PostMapping(path="/upload/detallefactura",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir archivo, seleccionar el archivo ejemplo (Consulta1.scv) cuenta con 11k registros")
    public ResponseEntity<String> uploadFileDetalleFactura(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.saveFacturaDetalle(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                log.info(e+"");
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
