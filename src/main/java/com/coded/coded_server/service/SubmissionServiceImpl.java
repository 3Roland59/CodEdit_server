package com.coded.coded_server.service;

import com.coded.coded_server.dto.SubmissionRequestDto;
import com.coded.coded_server.dto.SubmissionResponseDto;
import com.coded.coded_server.exception.ResourceAlreadyExistsException;
import com.coded.coded_server.exception.ResourceNotFoundException;
import com.coded.coded_server.mapper.SubmissionMapper;
import com.coded.coded_server.model.Challenge;
import com.coded.coded_server.model.Submission;
import com.coded.coded_server.repository.ChallengeRepository;
import com.coded.coded_server.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final ChallengeRepository challengeRepository;

    @Override
    public SubmissionResponseDto createSubmission(SubmissionRequestDto dto) {
        Optional<Submission> existing = submissionRepository.findByStudentId(dto.getStudentId());

        if (existing.isPresent()) {
            throw new ResourceAlreadyExistsException("Submission already exists for student ID: " + dto.getStudentId());
        }

        Challenge challenge = challengeRepository.findById(dto.getChallengeId())
                .orElseThrow(() -> new RuntimeException("Challenge not found"));

        Submission submission = SubmissionMapper.toEntity(dto, challenge);
        // TODO: Add evaluation logic (optional)
        Submission saved = submissionRepository.save(submission);

        return SubmissionMapper.toResponse(saved);
    }

    @Override
    public List<SubmissionResponseDto> getSubmissionsByChallengeId(UUID challengeId) {
        return submissionRepository.findByChallengeId(challengeId).stream()
                .map(SubmissionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SubmissionResponseDto getSubmissionsByStudentId(String studentId) {
        Submission submission = submissionRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found for student ID: " + studentId));

        return SubmissionMapper.toResponse(submission);
    }
}
