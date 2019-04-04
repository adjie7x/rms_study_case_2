package com.mitrais.rms_study_case_2.controller.auth.admin;

import com.mitrais.rms_study_case_2.controller.form.UserForm;
import com.mitrais.rms_study_case_2.model.Authority;
import com.mitrais.rms_study_case_2.model.User;
import com.mitrais.rms_study_case_2.service.AuthorityService;
import com.mitrais.rms_study_case_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/auth/admin/registration_user")
public class RegistrationController {

    private String pageTitle = "head.title.admin.user.registration";

    private String contentTitle = "content.title.registration_user";

    private final String menuOpenCss = "menu-open";

    private final String activeCss = "active";

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

    @ModelAttribute("roles")
    public List<Authority> getRoles() {
        List<Authority> roles = authorityService.getAll();
        return roles;
    }

    @ModelAttribute("pagetitle")
    public String getPageTitle(){

        return pageTitle;
    }

    @ModelAttribute("contenttitle")
    public String getContentTitle(){

        return contentTitle;
    }

    @ModelAttribute("user_mgmt_menu_open")
    public String getMenuOpenCss(){

        return menuOpenCss;
    }

    @ModelAttribute("reg_user_active")
    public String getActiveCss(){

        return activeCss;
    }

    @ModelAttribute("parent_user_mgmt_active")
    public String getParentActiveCss(){

        return activeCss;
    }

    @GetMapping
    public ModelAndView registrationPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/registration_user.html");
        mv.addObject("userForm", new UserForm());

        return mv;
    }

    @PostMapping
    public ModelAndView doUserRegistration(@Valid UserForm userForm, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView();
        User userExists = userService.findUserByEmail(userForm.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if (bindingResult.hasErrors()) {
            mv.setViewName("/admin/registration_user.html");
        }


        return mv;
    }

}
