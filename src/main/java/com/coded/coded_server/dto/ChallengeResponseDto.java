package com.coded.coded_server.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ChallengeResponseDto {
    private UUID id;
    private UUID userId;
    private String title;
    private String description;
    private Integer time;
    private String languages;
}
