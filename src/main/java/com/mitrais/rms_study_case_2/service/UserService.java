package com.mitrais.rms_study_case_2.service;

import com.mitrais.rms_study_case_2.handler.UserNotActivatedException;
import com.mitrais.rms_study_case_2.model.Authority;
import com.mitrais.rms_study_case_2.model.Role;
import com.mitrais.rms_study_case_2.model.SecuredUserDetail;
import com.mitrais.rms_study_case_2.model.User;
import com.mitrais.rms_study_case_2.repository.AuthorityRepository;
import com.mitrais.rms_study_case_2.repository.RoleRepository;
import com.mitrais.rms_study_case_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthorityRepository authorityRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       AuthorityRepository authorityRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//    public void saveUser(User user, String roleId) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        Optional<Role> optionalRole = roleRepository.findById(roleId);
//        if (optionalRole.isPresent()){
//            Role userRole = optionalRole.get();
//            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
//            userRepository.save(user);
//        }
//
//    }

    public void saveUser(User user, String authorityId) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Optional<Authority> authority = authorityRepository.findById(authorityId);
        if (authority.isPresent()){
            Authority userAuthority = authority.get();
            user.setAuthorities(new HashSet<>(Arrays.asList(userAuthority)));
            userRepository.save(user);
        }

    }

    public Optional<User> findUserById(String id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public List<User> getAll(){

        return userRepository.findAll();
    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//
//        User user = userRepository.findByEmailOrUsername(s,s);
//        if (user != null){
//            detailsChecker.check(user);
//        }
//
//        return user;
//    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<User> userFromDatabase = userRepository.findByEmailOrUsername(login,login);
        return userFromDatabase.map(user -> {
            if (!user.isActive()) {
                throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
            }

            return new SecuredUserDetail(user);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " +
                "database"));
    }
}
