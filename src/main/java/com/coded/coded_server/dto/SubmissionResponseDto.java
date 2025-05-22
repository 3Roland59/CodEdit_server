package com.coded.coded_server.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SubmissionResponseDto {
    private UUID id;
    private String studentId;
    private String studentName;
    private UUID challengeId;
    private String code;
    private String language;
    private String result;
    private double score;
    private String scoreBreakdown;
    private LocalDateTime createdAt;
}
