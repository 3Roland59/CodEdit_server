package com.coded.coded_server.mapper;

import com.coded.coded_server.dto.UserRequestDto;
import com.coded.coded_server.dto.UserResponseDto;
import com.coded.coded_server.model.User;

public class UserMapper {

    public static User toEntity(UserRequestDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public static UserResponseDto toResponse(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
}