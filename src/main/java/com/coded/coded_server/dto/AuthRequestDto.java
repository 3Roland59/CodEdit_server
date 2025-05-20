package com.coded.coded_server.dto;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String email;
    private String password;
}
