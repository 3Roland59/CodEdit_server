package com.coded.coded_server.dto;

import java.util.List;

import lombok.Data;

@Data
public class ExecutionResponseDto {
    private String submissionId;
    private Boolean success;
    private String message;
    private double score;
    private String output;
    private String error;
    private List<TestCaseResultRequestDto> testCaseResults;

}