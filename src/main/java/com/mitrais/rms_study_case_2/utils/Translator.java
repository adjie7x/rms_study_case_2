package com.mitrais.rms_study_case_2.utils;

import org.springframework.stereotype.Component;

@Component("translator")
public class Translator {

    public static final String APPLICATION_TXT = "Pagi";

    public String translateToFrench(String param){

        return param.toUpperCase();
    }


}
