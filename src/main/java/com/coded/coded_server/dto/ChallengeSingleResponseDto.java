package com.coded.coded_server.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ChallengeSingleResponseDto {
    private UUID id;
    private UserResponseDto user;
    private String title;
    private String description;
    private String languages;
}
