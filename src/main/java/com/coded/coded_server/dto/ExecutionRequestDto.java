package com.coded.coded_server.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExecutionRequestDto {
    private String submissionId;
    private String code;
    private String language;
    private List<TestCaseRequestDto> testCases;
}
