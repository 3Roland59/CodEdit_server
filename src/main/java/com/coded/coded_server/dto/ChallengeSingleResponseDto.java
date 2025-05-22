package com.coded.coded_server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeSingleResponseDto {
    private UUID id;
    private UserResponseDto user;
    private String title;
    private String description;
    private Integer time;
    private String languages;
    private List<TestCaseResponseDto> testCases;
}
