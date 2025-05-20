package com.coded.coded_server.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.coded.coded_server.dto.*;
import com.coded.coded_server.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping("/auth/register")
    public UserResponseDto createUser(@RequestBody UserRequestDto dto) {
        return userService.createUser(dto);
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserResponseDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
