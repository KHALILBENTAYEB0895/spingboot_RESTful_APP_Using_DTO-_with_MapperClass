package com.sp.spingboot_restfull.service;

import com.sp.spingboot_restfull.dto.UserDto;
import com.sp.spingboot_restfull.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    void deleteUser(Long userId);
}
