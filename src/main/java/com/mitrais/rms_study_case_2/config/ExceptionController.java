package com.mitrais.rms_study_case_2.config;


import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    private String pageTitle = "error.500.page.title";

    private String contentTitle = "error.500.content.title";

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception e)   {
        LoggerFactory.getLogger(getClass()).info( "Request: " + request.getRequestURL() + " raised " + e);
        ModelAndView mv = new ModelAndView("error.html");
        mv.addObject("pagetitle", pageTitle);
        mv.addObject("contenttitle",contentTitle);
        return mv;
    }
}
