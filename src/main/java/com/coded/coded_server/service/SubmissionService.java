package com.coded.coded_server.service;

import com.coded.coded_server.dto.SubmissionRequestDto;
import com.coded.coded_server.dto.SubmissionResponseDto;

import java.util.List;
import java.util.UUID;

public interface SubmissionService {
    SubmissionResponseDto createSubmission(SubmissionRequestDto dto);
    List<SubmissionResponseDto> getSubmissionsByChallengeId(UUID challengeId);
    SubmissionResponseDto getSubmissionsByStudentId(String studentId);
}
