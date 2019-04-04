package com.mitrais.rms_study_case_2.controller.auth;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    private String pageTitle = "head.title.home";

    private String contentTitle = "content.title.home";

    private String menuOpenCss = "menu-open";

    private String activeCss = "active";

    @ModelAttribute("pagetitle")
    public String getPageTitle(){

        return pageTitle;
    }

    @ModelAttribute("contenttitle")
    public String getContentTitle(){

        return contentTitle;
    }

    @ModelAttribute("home_menu_open")
    public String getMenuOpenCss(){

        return menuOpenCss;
    }

    @ModelAttribute("home_active")
    public String getActiveCss(){

        return activeCss;
    }

    @GetMapping
    public ModelAndView goToHome(HttpServletRequest request, Principal principal, HttpSession httpSession){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("home.html");
        return mv;
    }

}
