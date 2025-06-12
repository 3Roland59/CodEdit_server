package com.coded.coded_server.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SubmissionCreateResponseDto {
    private UUID id;
    private String studentId;
    private String submissionKey;
    private String studentName;
    private UUID challengeId;
    private String code;
    private String language;
    private Boolean success;
    private LocalDateTime createdAt;
}
