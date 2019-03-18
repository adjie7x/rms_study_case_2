package com.mitrais.thymeleaf_layout_demo.utils;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component("dateFormatter")
public class DateFormatter {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public String getCurrentDate()
    {
        return new DateTime(new Date()).toString(DATE_FORMAT);
    }

}
