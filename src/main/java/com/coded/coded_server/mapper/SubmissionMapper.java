package com.coded.coded_server.mapper;

import com.coded.coded_server.dto.SubmissionRequestDto;
import com.coded.coded_server.dto.SubmissionResponseDto;
import com.coded.coded_server.dto.SubmissionCreateResponseDto;
import com.coded.coded_server.dto.TestCaseResultResponseDto;
import com.coded.coded_server.model.Challenge;
import com.coded.coded_server.model.Submission;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SubmissionMapper {

    public static Submission toEntity(SubmissionRequestDto dto, Challenge challenge) {
        return Submission.builder()
                .studentId(dto.getStudentId())
                .submissionKey(dto.getSubmissionKey())
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
        dto.setSubmissionKey(submission.getSubmissionKey());
        dto.setStudentName(submission.getStudentName());
        dto.setChallengeId(submission.getChallenge().getId());
        dto.setCode(submission.getCode());
        dto.setLanguage(submission.getLanguage());
        dto.setSuccess(submission.getSuccess());
        dto.setScore(submission.getScore());
        dto.setMessage(submission.getMessage());
        dto.setCreatedAt(submission.getCreatedAt());

        List<TestCaseResultResponseDto> testCasesResult = submission.getTestCaseResults() != null
                ? submission.getTestCaseResults().stream()
                    .map(TestCaseResultMapper::toResponse)
                    .collect(Collectors.toList())
                : List.of();

        dto.setTestCaseResult(testCasesResult);
        return dto;
    }

    public static SubmissionCreateResponseDto toCreateResponse(Submission submission) {
        SubmissionCreateResponseDto dto = new SubmissionCreateResponseDto();
        dto.setId(submission.getId());
        dto.setStudentId(submission.getStudentId());
        dto.setSubmissionKey(submission.getSubmissionKey());
        dto.setStudentName(submission.getStudentName());
        dto.setChallengeId(submission.getChallenge().getId());
        dto.setCode(submission.getCode());
        dto.setLanguage(submission.getLanguage());
        dto.setCreatedAt(submission.getCreatedAt());
        return dto;
    }
}
