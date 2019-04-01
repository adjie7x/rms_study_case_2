package com.mitrais.rms_study_case_2.repository;

import com.mitrais.rms_study_case_2.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("authorityRepository")
public interface AuthorityRepository extends JpaRepository<Authority,String> {
}
