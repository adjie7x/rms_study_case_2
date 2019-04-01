package com.mitrais.rms_study_case_2.controller.auth;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ModelAndView goToHome(Principal principal, HttpSession httpSession){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("home.html");
        mv.addObject("pagetitle","head.title.home");
        mv.addObject("contenttitle","content.title.home");
        return mv;
    }

}
