package com.mitrais.rms_study_case_2.controller.mapper;

import com.mitrais.rms_study_case_2.controller.mapper.dto.UserDTO;
import com.mitrais.rms_study_case_2.model.User;

import java.util.List;
import java.util.stream.Collectors;

public interface UserDTOOwnMapper {

    public List<UserDTO> usersToUserDTOs(List<User> users);

    default UserDTO userToUserDto(User user){

        if(user == null){
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullname(user.getFullname());
        userDTO.setEmail(user.getEmail());
        userDTO.setActive(user.isActive());

        List<String> roles = user.getAuthorities().stream().map(authority -> new String(authority.getAuthority())).collect(Collectors.toList());

        userDTO.setSelectedRole(roles.get(0));

        return userDTO;
    }

}
