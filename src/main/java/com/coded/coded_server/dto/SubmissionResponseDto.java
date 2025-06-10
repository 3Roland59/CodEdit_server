package com.coded.coded_server.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class SubmissionResponseDto {
    private UUID id;
    private String studentId;
    private String studentName;
    private UUID challengeId;
    private String code;
    private String language;
    private Boolean success;
    private double score;
    private String message;
    private List<TestCaseResultResponseDto> testCaseResult;
    private LocalDateTime createdAt;
}
