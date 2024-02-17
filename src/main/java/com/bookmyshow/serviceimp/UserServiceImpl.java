package com.bookmyshow.serviceimp;


import com.bookmyshow.Repository.UserRepository;
import com.bookmyshow.entity.Users;
import com.bookmyshow.dto.UserDto;
import com.bookmyshow.exception.ResourceNotFoundException;
import com.bookmyshow.services.UserServices;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto addUser(UserDto userDto) {
//        String encode = passwordEncoder.encode(userDto.getPassword());
//        userDto.setPassword(encode);

        Users users = userDtoToUser(userDto);
        Users saved = userRepository.save(users);
        return userToUserDto(saved);

    }


    @Override
    public UserDto getUserById(Integer userId){
        Users users = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Id is not found"));

        return modelMapper.map(users,UserDto.class);    }

    @Override
    public UserDto getUserByEmail(String email) {
        return modelMapper.map((userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User not found"))),UserDto.class);
    }


    public UserDto userToUserDto(Users users) {
        UserDto userDto = modelMapper.map(users, UserDto.class);
        return userDto;

    }

    public Users userDtoToUser(UserDto userDto) {
        Users map = modelMapper.map(userDto, Users.class);
        return map;
    }



}