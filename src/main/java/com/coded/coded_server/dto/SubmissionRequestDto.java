package com.coded.coded_server.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SubmissionRequestDto {
    private String studentId;
    private String studentName;
    private UUID challengeId;
    private String code;
    private String language;
}
