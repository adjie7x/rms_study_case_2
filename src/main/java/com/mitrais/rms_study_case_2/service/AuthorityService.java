package com.mitrais.rms_study_case_2.service;

import com.mitrais.rms_study_case_2.model.Authority;
import com.mitrais.rms_study_case_2.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authorityService")
public class AuthorityService {

    private AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository){
        this.authorityRepository = authorityRepository;
    }

    public Authority saveAuthority(Authority authority){
        authority = authorityRepository.save(authority);

        return authority;
    }

    public List<Authority> getAll(){
        return authorityRepository.findAll();
    }
}
