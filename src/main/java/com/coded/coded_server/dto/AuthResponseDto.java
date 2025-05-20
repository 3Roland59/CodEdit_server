package com.coded.coded_server.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private UserResponseDto user;
}
