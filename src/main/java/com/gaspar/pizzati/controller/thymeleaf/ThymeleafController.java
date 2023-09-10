package com.gaspar.pizzati.controller.thymeleaf;

import com.gaspar.pizzati.model.LoggerColored;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/thymeleaf")
@CrossOrigin(origins = "*")
public class ThymeleafController {
    private final LoggerColored log = new LoggerColored(LoggerFactory.getLogger(getClass()));
    @GetMapping
    public String showHomePage(HttpServletRequest request){
        log.infoB(request.getRemoteAddr());
        log.infoB(request.getRemoteUser());
        log.infoB(request.getRemoteHost());
        log.infoB(request.getRemotePort()+"");

        log.info("/thymeleaf");
        return "HomePage";
    }
}
