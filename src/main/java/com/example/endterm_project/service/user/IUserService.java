package com.example.endterm_project.service.user;


import com.example.endterm_project.dto.UserDto;
import com.example.endterm_project.entity.User;

import java.util.List;

public interface IUserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
