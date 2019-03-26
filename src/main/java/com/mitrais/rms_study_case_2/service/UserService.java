package com.mitrais.rms_study_case_2.service;

import com.mitrais.rms_study_case_2.model.Role;
import com.mitrais.rms_study_case_2.model.User;
import com.mitrais.rms_study_case_2.repository.RoleRepository;
import com.mitrais.rms_study_case_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user, String roleId) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (optionalRole.isPresent()){
            Role userRole = optionalRole.get();
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            userRepository.save(user);
        }

    }

}
