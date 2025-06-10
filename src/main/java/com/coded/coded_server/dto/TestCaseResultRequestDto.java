package com.coded.coded_server.dto;

import lombok.Data;

@Data
public class TestCaseResultRequestDto {
    String input;
    String expected;
    String output;
    Boolean passed;
    String executionTime;
    String errorMessage;
}
