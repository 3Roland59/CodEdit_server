package com.coded.coded_server.dto;

import java.util.UUID;

import lombok.*;

@Data
@Builder
public class TestCaseResponseDto {
    private UUID id;
    private String inputDataType;
    private String inputValue;
    private String outputDataType;
    private String outputValue;
}
