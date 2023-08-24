package com.gaspar.pizzati.controller;

import com.gaspar.pizzati.entity.Factura;
import com.gaspar.pizzati.helper.FacturaExcelExport;
import com.gaspar.pizzati.helper.FacturaExcelExportV2;
import com.gaspar.pizzati.model.LoggerColored;
import com.gaspar.pizzati.service.IFacturaService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {
    private final LoggerColored log = new LoggerColored(LoggerFactory.getLogger(getClass()));
    private final IFacturaService service;

    public FacturaController(IFacturaService service) {
        this.service = service;
    }

    @GetMapping("/{idFactura}")
    public Factura getFactura(
            @PathVariable Long idFactura
    ){
        return service.getFactura(idFactura);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Factura> getFacturaCliente(
        @PathVariable Long idCliente
    ){
        return service.getFacturasCliente(idCliente);
    }

    @GetMapping("/excelExport/{idCliente}")
    public ModelAndView exportToExcel(
            @PathVariable Long idCliente
    ) {
        log.info("id"+idCliente);
        ModelAndView mav = new ModelAndView();
        mav.setView(new FacturaExcelExport());
        //read data from DB
        List<Factura> list= service.getFacturasCliente(idCliente);
        //send to excelImpl class
        mav.addObject("facturas", list);
        return mav;
    }

    @GetMapping("/excelExport/v2/{idCliente}")
    public void exportToExcel(
            @PathVariable Long idCliente,
            HttpServletResponse response
    ) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Factura> list= service.getFacturasCliente(idCliente);
        FacturaExcelExportV2 facturaExcelExportV2 = new FacturaExcelExportV2(list);
        facturaExcelExportV2.export(response);
    }
}
