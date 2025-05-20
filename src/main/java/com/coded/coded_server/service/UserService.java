package com.coded.coded_server.service;

import com.coded.coded_server.dto.UserRequestDto;
import com.coded.coded_server.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(UUID id);
    void deleteUser(UUID id);
}

