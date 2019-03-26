package com.mitrais.rms_study_case_2.repository;

import com.mitrais.rms_study_case_2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,String> {

    User findByEmail(String email);
}
