package com.gaspar.pizzati.helper;

import com.gaspar.pizzati.entity.Factura;
import com.gaspar.pizzati.repository.FacturaRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class FacturaExcelExport  extends AbstractXlsxView {

     @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.addHeader("Content-Disposition", "attachment;fileName=InvoiceData.xlsx");

        @SuppressWarnings("unchecked")
        List<Factura> list = (List<Factura>)model.get("facturas");

        Sheet sheet = workbook.createSheet("facturas");

        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("idFactura");
        row0.createCell(1).setCellValue("numeroFactura");
        row0.createCell(2).setCellValue("idCliente");
        row0.createCell(3).setCellValue("totalFactura");

        int rowNum = 1;
        for(Factura spec : list) {
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
