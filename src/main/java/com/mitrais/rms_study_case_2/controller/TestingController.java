package com.mitrais.rms_study_case_2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TestingController {

    @GetMapping
    public String testTemplateDefault(Model model){

        model.addAttribute("username", "aji");
        model.addAttribute("value1", "value1");
        model.addAttribute("value2", "value2");
        model.addAttribute("onevar", "onevar");
        model.addAttribute("twovar", 42);
//        model.addAttribute("page-title", "head.title.template");
        model.addAttribute("pagetitle", "head.title.template");

        return "template.html";
    }

}
