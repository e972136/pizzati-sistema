package com.gaspar.pizzati.helper;

import com.gaspar.pizzati.entity.Cliente;
import com.gaspar.pizzati.entity.Factura;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVParser;

public class CSVHelper {
    public static String TYPE="text/csv";
    static String[] HEADERS_CLIENTE = { "IDCliente", "NaturalezaCliente", "Nombre", "Direccion","Departamento", "Ciudad", "IDVendedor" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }
    public static List<Cliente> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withDelimiter(';'))) {




            List<Cliente> elementos = new ArrayList<>();

            List<CSVRecord> csvRecords = csvParser.getRecords();
            System.out.println("Size:" + csvRecords.size());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (CSVRecord csvRecord : csvRecords) {

                try{
                    Cliente tutorial = Cliente.builder()
                            .idCliente(Long.parseLong(csvRecord.get(0)))
                            .naturalezaCliente(csvRecord.get(1).charAt(0))
                            .nombre(csvRecord.get(2))
                            .direccion(csvRecord.get(3))
                            .departamento(csvRecord.get(4))
                            .ciudad(csvRecord.get(5))
                            .IdVendedor(Long.parseLong(csvRecord.get(6)))
                            .build();
                    elementos.add(tutorial);
                }catch (Exception e){
                    System.out.println(e+""+csvRecord);
                }
            }


            return elementos;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<Factura> csvToFactura(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withDelimiter(';'))) {




            List<Factura> elementos = new ArrayList<>();

            List<CSVRecord> csvRecords = csvParser.getRecords();
            System.out.println("Size:" + csvRecords.size());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (CSVRecord csvRecord : csvRecords) {

                try{
                    Factura tutorial = Factura.builder()
                            .idFactura(Long.parseLong(csvRecord.get(0)))
                            .numeroFactura(Long.parseLong(csvRecord.get(1)))
                            .idCliente(Long.parseLong(csvRecord.get(2)))
                            .idVendedor(Long.parseLong(csvRecord.get(3)))
//                            .fechaFactura(LocalDate.parse(csvRecord.get(4),formatter))
                            .fechaFactura(obtenerFecha(csvRecord.get(4)))
                            .subTotal(Double.parseDouble(csvRecord.get(5)))
                            .impuesto(Double.parseDouble(csvRecord.get(6)))
                            .descuento(Double.parseDouble(csvRecord.get(7)))
                            .totalFactura(Double.parseDouble(csvRecord.get(8)))
                            .build();
                    elementos.add(tutorial);
                }catch (Exception e){
                    System.out.println(e+""+csvRecord);
                }
            }
            return elementos;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    private static LocalDate obtenerFecha(String s) {
        String elementos[] = s.split("/");
        return LocalDate.of(Integer.parseInt(elementos[2]),Integer.parseInt(elementos[1]),Integer.parseInt(elementos[0]));
    }
}
