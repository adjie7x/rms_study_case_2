package com.mitrais.rms_study_case_2.service;

import com.mitrais.rms_study_case_2.model.Role;
import com.mitrais.rms_study_case_2.repository.RoleRepository;
import com.mitrais.rms_study_case_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RoleService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Role saveRole(Role role){
        role = roleRepository.save(role);
        return role;
    }
}
