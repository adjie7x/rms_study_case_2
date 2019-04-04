package com.mitrais.rms_study_case_2.controller.mapper.impl;

import com.mitrais.rms_study_case_2.controller.mapper.UserDTOOwnMapper;
import com.mitrais.rms_study_case_2.controller.mapper.dto.UserDTO;
import com.mitrais.rms_study_case_2.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("userDTOOwnMapper")
public class UserDTOOwnMapperImpl implements UserDTOOwnMapper {


    @Override
    public List<UserDTO> usersToUserDTOs(List<User> users) {

        if (users == null) {
            return null;
        } else {
            List<UserDTO> list = new ArrayList();
            Iterator var3 = users.iterator();

            while(var3.hasNext()) {
                User user = (User) var3.next();
                list.add(this.userToUserDto(user));
            }

            return list;
        }
    }
}
