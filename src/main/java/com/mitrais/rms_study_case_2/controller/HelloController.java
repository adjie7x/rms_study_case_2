package com.mitrais.rms_study_case_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String testDefault(Model model){

        model.addAttribute("name", "aji");
        model.addAttribute("formatted", "<b>blue</b>");
        DeveloperResource[] devResources = {
                new DeveloperResource("Google",
                        "http://www.google.com"),
                new DeveloperResource("Stackoverflow",
                        "http://www.stackoverflow.com"),
                new DeveloperResource("W3Schools",
                        "http://www.w3schools.com")
        };
        model.addAttribute("resources", devResources);


        return "hello.html";
    }

    @GetMapping("js")
    public String js(Model model){

        model.addAttribute("name", "aji");

        return "hello.js";
    }


    public static final class DeveloperResource {
        private final String name;
        private final String url;

        public DeveloperResource(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}
