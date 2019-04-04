package com.mitrais.rms_study_case_2.controller.auth.admin;

import com.mitrais.rms_study_case_2.controller.form.UserForm;
import com.mitrais.rms_study_case_2.controller.mapper.UserDTOOwnMapper;
import com.mitrais.rms_study_case_2.controller.mapper.dto.UserDTO;
import com.mitrais.rms_study_case_2.model.Authority;
import com.mitrais.rms_study_case_2.model.User;
import com.mitrais.rms_study_case_2.service.AuthorityService;
import com.mitrais.rms_study_case_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auth/admin/manage_users")
public class UserManagementController {

    private String pageTitle = "head.title.admin.user.management";

    private String contentTitle = "content.title.manage_users";

    private final String menuOpenCss = "menu-open";

    private final String activeCss = "active";

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDTOOwnMapper userDTOOwnMapper;

    @ModelAttribute("roles")
    public List<Authority> getRoles() {
        List<Authority> roles = authorityService.getAll();
        return roles;
    }

    @ModelAttribute("users")
    public List<UserDTO> getUserDtos(){
        List<User> users = userService.getAll();

        return userDTOOwnMapper.usersToUserDTOs(users);
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

    @ModelAttribute("user_mgmt_active")
    public String getActiveCss(){

        return activeCss;
    }

    @ModelAttribute("parent_user_mgmt_active")
    public String getParentActiveCss(){

        return activeCss;
    }

    @GetMapping
    public ModelAndView registrationPage(@RequestParam(value = "userId", required = false) String userId){
        ModelAndView mv = new ModelAndView();
        UserForm userForm = new UserForm();
        if (userId != null && !"".equals(userId)){
            Optional<User> optionalUser = userService.findUserById(userId);
            if (optionalUser.isPresent()){
                User user = optionalUser.get();
                userForm.setFullname(user.getFullname());
                userForm.setEmail(user.getEmail());
                userForm.setId(user.getId());

                List<String> roles = user.getAuthorities().stream().map(authority -> new String(authority.getId())).collect(Collectors.toList());
                userForm.setSelectedRole(roles.get(0));
            }
        }

        mv.addObject("userForm", userForm);
        mv.setViewName("/admin/manage_users.html");
        return mv;
    }

    @PostMapping
    @RequestMapping(params = "action=update")
    public ModelAndView doUpdateUserRegistration(UserForm userForm, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/manage_users.html");
        User userExists = userService.findUserByEmail(userForm.getEmail());
        if (userExists == null) {

            bindingResult
                    .rejectValue("email", "error.user",
                            "There is no user registered with the email provided");
        }else{
            if ((userForm.getFullname() == null || "".equals(userForm.getFullname()))){
                bindingResult
                        .rejectValue("fullname", "error.user",
                                "You have to fill full name!");
            }

            if((userForm.getSelectedRole() == null || "".equals(userForm.getSelectedRole()))){
                bindingResult
                        .rejectValue("selectedRole", "error.user",
                                "You have to select 1 role!");
            }
        }

        if (!bindingResult.hasErrors()) {
            mv.addObject("userForm",new UserForm());
            mv.setViewName("/admin/manage_users.html");
        }


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
            mv.setViewName("/admin/manage_users.html");
        }else {
            User user = new User();
            user.setFullname(userForm.getFullname());
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword());
            user.setUsername(userForm.getEmail());

            userService.saveUser(user,userForm.getSelectedRole());
            mv.addObject("userForm",new UserForm());
            mv.addObject("users", getUserDtos());
            mv.setViewName("/admin/manage_users.html");
        }


        return mv;
    }
}
