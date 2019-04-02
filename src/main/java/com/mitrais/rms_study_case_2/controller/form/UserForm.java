package com.mitrais.rms_study_case_2.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserForm {

    @NotEmpty(message = "*Please provide your Full name")
    private String fullname;

    @NotEmpty(message = "*Please provide an email")
    private String email;

    @NotEmpty(message = "*Please select 1 role")
    private String selectedRole;

    @NotEmpty(message = "*Please provide your password")
    private String password;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
