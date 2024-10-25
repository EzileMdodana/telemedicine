package com.example.demo.models.user.profiles;

import com.example.demo.models.user.User;
import com.example.demo.models.user.dtos.CreateUserDto;
import com.example.demo.models.user.dtos.GetUserDto;
import com.example.demo.models.user.dtos.UpdateUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    GetUserDto toGetUserDto(User user);

    User toUser(CreateUserDto createUserDto);

    User toUser(UpdateUserDto updateUserDto);
}
