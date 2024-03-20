package com.sp.spingboot_restfull.service.Impl;

import com.sp.spingboot_restfull.dto.UserDto;
import com.sp.spingboot_restfull.entity.User;
import com.sp.spingboot_restfull.mapper.UserMapper;
import com.sp.spingboot_restfull.repository.UserRepository;
import com.sp.spingboot_restfull.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto into User JPA Entity
        //User user = UserMapper.mapToUser(userDto);
        User user = modelMapper.map(userDto, User.class);

        User savedUser = userRepository.save(user);
        // Convert User JPA Entity into UserDto
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
                return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        //return UserMapper.mapToUserDto( user.get());
        return modelMapper.map(user.get(), UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List <User> users = userRepository.findAll();
       /* List<UserDto> usersDto = new ArrayList<UserDto>();
        users.forEach(user -> {
            usersDto.add(UserMapper.mapToUserDto(user));
        });
        return usersDto;*/
        //return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        return users.stream().map((user)->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        //UserDto updatedUser = UserMapper.mapToUserDto(userRepository.save(existingUser));
        UserDto updatedUser = modelMapper.map(userRepository.save(existingUser),UserDto.class);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
