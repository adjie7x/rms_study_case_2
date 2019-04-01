package com.mitrais.rms_study_case_2.controller;

import com.mitrais.rms_study_case_2.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes({"currentUser", "currentUserId"})
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ModelAndView getLoginPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login.html");
        return mv;
    }

    @PostMapping
    public  ModelAndView postLogin(HttpSession httpSession, ModelMap model){

        // read principal out of security context and set it to session
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        User loggedInUser = ((User) authentication.getPrincipal());
        validatePrinciple(loggedInUser);

        model.addAttribute("currentUserId", loggedInUser.getId());
        model.addAttribute("currentUser", loggedInUser.getUsername());
        httpSession.setAttribute("userId", loggedInUser.getId());

        return new ModelAndView("redirect:/",model);
    }

//    @PostMapping
//    public  String postLogin(Model model, HttpSession httpSession){
//
//        // read principal out of security context and set it to session
//        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//
//        User loggedInUser = ((User) authentication.getPrincipal());
//        validatePrinciple(loggedInUser);
//
//        model.addAttribute("currentUserId", loggedInUser.getId());
//        model.addAttribute("currentUser", loggedInUser.getUsername());
//        httpSession.setAttribute("userId", loggedInUser.getId());
//        return "redirect:/";
//    }

    private void validatePrinciple(Object principal) {
        if (principal == null) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
    }
}
