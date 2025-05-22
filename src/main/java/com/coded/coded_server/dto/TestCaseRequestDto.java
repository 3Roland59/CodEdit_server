package com.coded.coded_server.dto;

import lombok.Data;

@Data
public class TestCaseRequestDto {
    private String inputDataType;
    private String inputValue;
    private String outputDataType;
    private String outputValue;
}
