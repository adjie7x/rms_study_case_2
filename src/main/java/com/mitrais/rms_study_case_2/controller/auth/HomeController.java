package com.mitrais.rms_study_case_2.controller.auth;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ModelAndView goToHome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home.html");
        mv.addObject("pagetitle","head.title.home");
        return mv;
    }

}
