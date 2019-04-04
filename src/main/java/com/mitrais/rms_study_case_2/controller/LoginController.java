package com.mitrais.rms_study_case_2.controller;

import com.mitrais.rms_study_case_2.model.SecuredUserDetail;
import com.mitrais.rms_study_case_2.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes({"currentUser", "currentUserId"})
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ModelAndView getLoginPage(Principal principal, HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login.html");
        // read principal out of security context and set it to session
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(principal != null){
            return new ModelAndView("redirect:/");
        }
        return mv;
    }

    @PostMapping
    public  ModelAndView postLogin(Principal principal, HttpSession httpSession, ModelMap model){

        // read principal out of security context and set it to session
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        User loggedInUser = ((SecuredUserDetail) authentication.getPrincipal()).getUserDetails();

//        model.addAttribute("currentUserId", loggedInUser.getId());
//        model.addAttribute("currentUser", loggedInUser.getUsername());
        httpSession.setAttribute("userId", loggedInUser.getId());
        List<String> roles = loggedInUser.getAuthorities().stream().map(authority -> new String(authority.getAuthority())).collect(Collectors.toList());
        httpSession.setAttribute("userRole", roles.get(0));
        httpSession.setAttribute("userFullname", loggedInUser.getFullname());

        return new ModelAndView("redirect:/",model);
    }

    @GetMapping
    @RequestMapping(value = "/logout")
    public String logout(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return "redirect:/";
    }

    private void validatePrinciple(Object principal) {
        if (!(principal instanceof SecuredUserDetail)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
    }
}
