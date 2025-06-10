package com.coded.coded_server.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCaseResultResponseDto {
    private UUID id;
    private String input;
    private String expected;
    private String output;
    private Boolean passed;
    private String executionTime;
    private String errorMessage;
}
