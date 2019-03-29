package com.mitrais.rms_study_case_2.controller;


import com.mitrais.rms_study_case_2.model.Role;
import com.mitrais.rms_study_case_2.model.User;
import com.mitrais.rms_study_case_2.service.RoleService;
import com.mitrais.rms_study_case_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/template")
public class TestingController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

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

    @GetMapping
    @RequestMapping("/login")
    public String testLoginPage(Model model){

        model.addAttribute("username", "aji");
        model.addAttribute("value1", "value1");
        model.addAttribute("value2", "value2");
        model.addAttribute("onevar", "onevar");
        model.addAttribute("twovar", 42);
//        model.addAttribute("page-title", "head.title.template");
        model.addAttribute("pagetitle", "head.title.template");

        return "login.html";
    }

    @PostMapping
    @RequestMapping("/saveRole")
    public @ResponseBody String saveRole(@RequestParam(name = "roleName")String roleName){

        Role role = new Role();
        role.setRoleName("ROLE_"+roleName);
        role = roleService.saveRole(role);

        return role.getId();
    }

    @PostMapping
    @RequestMapping("/saveUser")
    public @ResponseBody String saveUser(@RequestParam(name = "email")String email, @RequestParam(name = "pwd")String pwd, @RequestParam(name = "roleId")String roleId){

        User user = new User();
        user.setUsername(email);
        user.setEmail(email);
        user.setPassword(pwd);

        userService.saveUser(user,roleId);

        return "SUCCESS";
    }

}
