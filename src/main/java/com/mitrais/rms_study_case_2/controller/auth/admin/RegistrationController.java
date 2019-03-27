package com.mitrais.rms_study_case_2.controller.auth.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth/admin/registration")
public class RegistrationController {

    @GetMapping
    public ModelAndView registrationPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/registration_user.html");
        mv.addObject("pagetitle","head.title.admin.user.registration");
        return mv;
    }
}
