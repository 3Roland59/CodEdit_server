package com.coded.coded_server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeRequestDto {
    private String title;
    private String description;
    private Integer time;
    private String languages;
    private List<TestCaseRequestDto> testCases;
}
