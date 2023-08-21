package com.gaspar.pizzati.helper;

import com.gaspar.pizzati.entity.Factura;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FacturaExcelExportV2 {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Factura> listUsers;

    public FacturaExcelExportV2(List<Factura> listUsers) {
        this.listUsers = listUsers;
        workbook = new XSSFWorkbook();
    }

    public void export(HttpServletResponse response) throws IOException {
//        response.addHeader("Content-Disposition", "attachment;fileName=InvoiceData.xlsx");

        sheet = workbook.createSheet("facturas");

        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("idFactura");
        row0.createCell(1).setCellValue("numeroFactura");
        row0.createCell(2).setCellValue("idCliente");
        row0.createCell(3).setCellValue("totalFactura");

        int rowNum = 1;
        for(Factura spec : listUsers) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(spec.getIdFactura());
            row.createCell(1).setCellValue(spec.getNumeroFactura());
            row.createCell(2).setCellValue(spec.getIdCliente());
            row.createCell(3).setCellValue(spec.getTotalFactura());
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
