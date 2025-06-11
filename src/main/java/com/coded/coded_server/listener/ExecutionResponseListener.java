package com.coded.coded_server.listener;


import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.coded.coded_server.dto.ExecutionResponseDto;
import com.coded.coded_server.dto.TestCaseResultRequestDto;
import com.coded.coded_server.mapper.TestCaseResultMapper;
import com.coded.coded_server.model.Submission;
import com.coded.coded_server.model.TestCaseResult;
import com.coded.coded_server.repository.SubmissionRepository;
import com.coded.coded_server.repository.TestCaseResultRepository;

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
        submission.setSuccess(responseDto.getSuccess());
        submission.setMessage(responseDto.getMessage());

        submissionRepository.save(submission);

        for (TestCaseResultRequestDto tcDto : responseDto.getTestCaseResults()) {
            TestCaseResult testCaseResult = TestCaseResultMapper.toEntity(tcDto, submission);
            testCaseResultRepository.save(testCaseResult);
        }
    }
}
