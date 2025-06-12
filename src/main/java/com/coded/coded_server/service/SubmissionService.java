package com.coded.coded_server.service;

import com.coded.coded_server.dto.SubmissionRequestDto;
import com.coded.coded_server.dto.SubmissionResponseDto;
import com.coded.coded_server.dto.SubmissionCreateResponseDto;

import java.util.List;
import java.util.UUID;

public interface SubmissionService {
    SubmissionCreateResponseDto createSubmission(SubmissionRequestDto dto);
    List<SubmissionResponseDto> getSubmissionsByChallengeId(UUID challengeId);
    SubmissionResponseDto getSubmissionsByStudentId(String studentId, UUID challengeId, String submissionKey);
}
