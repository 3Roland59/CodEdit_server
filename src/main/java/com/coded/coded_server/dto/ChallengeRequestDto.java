package com.coded.coded_server.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ChallengeRequestDto {
    private UUID userId;
    private String title;
    private String description;
    private Integer time;
    private String languages;
}
