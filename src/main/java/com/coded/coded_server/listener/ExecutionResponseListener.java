package com.coded.coded_server.listener;

import com.coded.coded_server.dto.*;
import com.coded.coded_server.repository.*;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ExecutionResponseListener {

    private final SubmissionRepository submissionRepository;
    private final TestCaseResultRepository testCaseResultRepository;

    public ExecutionResponseListener(
        SubmissionRepository submissionRepository,
        TestCaseResultRepository testCaseResultRepository
    ) {
        this.submissionRepository = submissionRepository;
        this.testCaseResultRepository = testCaseResultRepository;
    }

    @RabbitListener(queues = "code_execution_responses")
    @Transactional
    public void handleExecutionResponse(ExecutionResponseDto responseDto) {
        String submissionId = responseDto.getSubmissionId();

        Submission submission = submissionRepository.findById(UUID.fromString(submissionId))
            .orElseThrow(() -> new RuntimeException("Submission not found: " + submissionId));

        submission.setScore(responseDto.getScore());
        submission.setSuccess(responseDto.isSuccess());
        submission.setMessage(responseDto.getMessage());

        submissionRepository.save(submission);

        for (TestCaseResultRequestDto tcDto : responseDto.getTestCaseResults()) {
            TestCaseResult testCaseResult = TestCaseResultMapper.toEntity(tcDto, submission);
            testCaseResultRepository.save(testCaseResult);
        }
    }
}
