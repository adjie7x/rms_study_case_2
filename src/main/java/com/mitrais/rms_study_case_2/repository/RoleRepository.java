package com.mitrais.rms_study_case_2.repository;

import com.mitrais.rms_study_case_2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role,String> {

    Role findByRoleName(String roleName);
}
