package com.coded.coded_server.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String username;
    private String email;
    private String password;
}
