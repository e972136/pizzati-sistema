package com.gaspar.pizzati.helper;

import com.gaspar.pizzati.model.Programador;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static String getCountryISO2FromISO3(String iso3Code) {
        iso3Code = StringUtils.trimToNull(iso3Code);
        if (iso3Code == null)
            return null;
        if (iso3Code.length() == 3) {
            for (String iso2Code : Locale.getISOCountries()) {
                Locale countryLocale = new Locale(iso2Code, iso2Code);
                String countryISO3 = StringUtils.trimToNull(countryLocale.getISO3Country());
                if (countryISO3 != null && countryISO3.equalsIgnoreCase(iso3Code)) {
                    return iso2Code;
                }
            }
        }
        return null;
    }

    public static String getCountryISO3FromISO2(String iso2Code){
        iso2Code = StringUtils.trimToNull(iso2Code);
        for (String iso2CodeAct : Locale.getISOCountries()) {
            if(!iso2CodeAct.equalsIgnoreCase(iso2Code)){
                continue;
            }
            Locale locale = new Locale("", iso2CodeAct);
            return locale.getISO3Country();
        }
        return null;
    }

    public static void main(String arg[]){
//        System.out.println(getCountryISO2FromISO3("CRI"));
//        System.out.println(getCountryISO3FromISO2("CR"));
        testMapFlatMap();
    }

    public static void testMapFlatMap(){

        List<Programador> programadores = List.of(
                new Programador("p1",List.of("java","pyton","angular")),
                new Programador("p2",List.of("ruby","angular","java")),
                new Programador("p2",List.of("react","spring","angular"))
        );

        programadores.stream()
                .map(Programador::getNombre)
                .forEach(System.out::println);

        programadores.stream()
                .flatMap(p->p.getHabilidades().stream())
                .collect(Collectors.toSet())
                .forEach(System.out::println);

    }
}
