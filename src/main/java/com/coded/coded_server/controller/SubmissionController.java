package com.coded.coded_server.controller;

import com.coded.coded_server.dto.SubmissionRequestDto;
import com.coded.coded_server.dto.SubmissionResponseDto;
import com.coded.coded_server.service.SubmissionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/submissions")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<SubmissionResponseDto> createSubmission(@RequestBody SubmissionRequestDto dto) {
        SubmissionResponseDto response = submissionService.createSubmission(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/challenge/{challengeId}")
    public ResponseEntity<List<SubmissionResponseDto>> getSubmissionsByChallengeId(@PathVariable UUID challengeId) {
        List<SubmissionResponseDto> responses = submissionService.getSubmissionsByChallengeId(challengeId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<SubmissionResponseDto> getSubmissionByStudentId(@PathVariable String studentId) {
        SubmissionResponseDto response = submissionService.getSubmissionsByStudentId(studentId);
        return ResponseEntity.ok(response);
    }
}
