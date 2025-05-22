package com.coded.coded_server.mapper;

import java.util.UUID;

import com.coded.coded_server.dto.TestCaseRequestDto;
import com.coded.coded_server.dto.TestCaseResponseDto;
import com.coded.coded_server.model.Challenge;
import com.coded.coded_server.model.TestCase;

public class TestCaseMapper {

    public static TestCase toEntity(TestCaseRequestDto dto, Challenge challenge) {
        TestCase testCase = new TestCase();
        testCase.setId(UUID.randomUUID());
        testCase.setInputDataType(dto.getInputDataType());
        testCase.setInputValue(dto.getInputValue());
        testCase.setOutputDataType(dto.getOutputDataType());
        testCase.setOutputValue(dto.getOutputValue());
        testCase.setChallenge(challenge);
        return testCase;
    }

    public static TestCaseResponseDto toResponse(TestCase testCase) {
        return TestCaseResponseDto.builder()
                .id(testCase.getId())
                .inputDataType(testCase.getInputDataType())
                .inputValue(testCase.getInputValue())
                .outputDataType(testCase.getOutputDataType())
                .outputValue(testCase.getOutputValue())
                .build();
    }
}
