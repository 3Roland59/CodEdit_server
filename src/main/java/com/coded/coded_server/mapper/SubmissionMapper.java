package com.coded.coded_server.mapper;

import com.coded.coded_server.dto.SubmissionRequestDto;
import com.coded.coded_server.dto.SubmissionResponseDto;
import com.coded.coded_server.model.Challenge;
import com.coded.coded_server.model.Submission;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubmissionMapper {

    public static Submission toEntity(SubmissionRequestDto dto, Challenge challenge) {
        return Submission.builder()
                .id(UUID.randomUUID())
                .studentId(dto.getStudentId())
                .studentName(dto.getStudentName())
                .challenge(challenge)
                .code(dto.getCode())
                .language(dto.getLanguage())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static SubmissionResponseDto toResponse(Submission submission) {
        SubmissionResponseDto dto = new SubmissionResponseDto();
        dto.setId(submission.getId());
        dto.setStudentId(submission.getStudentId());
        dto.setStudentName(submission.getStudentName());
        dto.setChallengeId(submission.getChallenge().getId());
        dto.setCode(submission.getCode());
        dto.setLanguage(submission.getLanguage());
        dto.setResult(submission.getResult());
        dto.setScore(submission.getScore());
        dto.setScoreBreakdown(submission.getScoreBreakdown());
        dto.setCreatedAt(submission.getCreatedAt());
        return dto;
    }
}
