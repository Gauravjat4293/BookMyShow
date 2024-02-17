package com.bookmyshow.services;

import com.bookmyshow.dto.UserDto;
import com.bookmyshow.entity.Users;

public interface UserServices {

   UserDto addUser(UserDto userDto);

    public UserDto getUserById(Integer userId);

    public UserDto getUserByEmail(String email);



}
