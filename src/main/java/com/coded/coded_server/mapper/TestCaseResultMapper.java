package com.coded.coded_server.mapper;


import com.coded.coded_server.dto.TestCaseResultRequestDto;
import com.coded.coded_server.dto.TestCaseResultResponseDto;
import com.coded.coded_server.model.Submission;
import com.coded.coded_server.model.TestCaseResult;

public class TestCaseResultMapper {
    public static TestCaseResult toEntity(TestCaseResultRequestDto dto, Submission submission) {
        TestCaseResult testCaseResult = new TestCaseResult();
        testCaseResult.setInput(dto.getInput());
        testCaseResult.setExpected(dto.getExpected());
        testCaseResult.setOutput(dto.getOutput());
        testCaseResult.setPassed(dto.getPassed());
        testCaseResult.setExecutionTime(dto.getExecutionTime());
        testCaseResult.setErrorMessage(dto.getErrorMessage());
        testCaseResult.setSubmission(submission);
        return testCaseResult;
    }

    public static TestCaseResultResponseDto toResponse(TestCaseResult testCaseResult) {
        return TestCaseResultResponseDto.builder()
                .id(testCaseResult.getId())
                .input(testCaseResult.getInput())
                .expected(testCaseResult.getExpected())
                .output(testCaseResult.getOutput())
                .passed(testCaseResult.getPassed())
                .executionTime(testCaseResult.getExecutionTime())
                .errorMessage(testCaseResult.getErrorMessage())
                .build();
    }
}
