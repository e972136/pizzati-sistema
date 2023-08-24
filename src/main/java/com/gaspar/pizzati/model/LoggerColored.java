package com.gaspar.pizzati.model;

import org.slf4j.Logger;

public class LoggerColored {
    private final Logger log ;
    private static final String ROJO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";
    private static final String AZUL = "\u001B[34m";
    private static final String BLANCO = "\u001B[0m";


    public LoggerColored(Logger log) {
        this.log = log;
    }

    public void info(String msg){
        log.info(ROJO + msg + BLANCO);
    }
    public void infoG(String msg){
        log.info(VERDE + msg + BLANCO);
    }
    public void infoB(String msg){
        log.info(AZUL + msg + BLANCO);
    }
}
