package com.coded.coded_server.service;

import com.coded.coded_server.dto.SubmissionRequestDto;
import com.coded.coded_server.dto.SubmissionResponseDto;
import com.coded.coded_server.dto.TestCaseRequestDto;
import com.coded.coded_server.dto.ExecutionRequestDto;
import com.coded.coded_server.dto.SubmissionCreateResponseDto;
import com.coded.coded_server.exception.ResourceAlreadyExistsException;
import com.coded.coded_server.exception.ResourceNotFoundException;
import com.coded.coded_server.exception.UnauthorizedException;
import com.coded.coded_server.mapper.SubmissionMapper;
import com.coded.coded_server.model.Challenge;
import com.coded.coded_server.model.Submission;
import com.coded.coded_server.repository.ChallengeRepository;
import com.coded.coded_server.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final RabbitTemplate rabbitTemplate;
    private final SubmissionRepository submissionRepository;
    private final ChallengeRepository challengeRepository;

        @Override
    public SubmissionCreateResponseDto createSubmission(SubmissionRequestDto dto) {
        Challenge challenge = challengeRepository.findById(dto.getChallengeId())
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found for ID: " + dto.getChallengeId()));

        Optional<Submission> existing = submissionRepository.findByStudentIdAndChallengeId(dto.getStudentId(), dto.getChallengeId());

        if (existing.isPresent()) {
            throw new ResourceAlreadyExistsException(
                    "Submission already exists for student ID: " + dto.getStudentId() +
                    " and challenge ID: " + dto.getChallengeId());
        }

        Submission submission = SubmissionMapper.toEntity(dto, challenge);

        Submission saved = submissionRepository.save(submission);

        List<TestCaseRequestDto> testCases = challenge.getTestCases().stream().map(tc -> {
            TestCaseRequestDto dtoTc = new TestCaseRequestDto();
            dtoTc.setInputDataType(tc.getInputDataType());
            dtoTc.setInputValue(tc.getInputValue());
            dtoTc.setOutputDataType(tc.getOutputDataType());
            dtoTc.setOutputValue(tc.getOutputValue());
            return dtoTc;
        }).toList();

        ExecutionRequestDto executionRequest = new ExecutionRequestDto();
        executionRequest.setSubmissionId(saved.getId().toString());
        executionRequest.setCode(saved.getCode());
        executionRequest.setLanguage(saved.getLanguage());
        executionRequest.setTestCases(testCases);

        rabbitTemplate.convertAndSend("code_execution_requests", executionRequest);

        return SubmissionMapper.toCreateResponse(saved);
    }

    @Override
    public List<SubmissionResponseDto> getSubmissionsByChallengeId(UUID challengeId) {
        return submissionRepository.findByChallengeId(challengeId).stream()
                .map(SubmissionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
public SubmissionResponseDto getSubmissionsByStudentId(String studentId, UUID challengeId, String submissionKey) {
    Submission submission = submissionRepository.findByStudentIdAndChallengeId(studentId, challengeId)
            .orElseThrow(() -> new ResourceNotFoundException("Submission for challenge not found for student ID: " + studentId));

    if (!submission.getSubmissionKey().equals(submissionKey)) {
        throw new UnauthorizedException("Invalid submission key provided for student ID: " + studentId);
    }

    return SubmissionMapper.toResponse(submission);
}

}
