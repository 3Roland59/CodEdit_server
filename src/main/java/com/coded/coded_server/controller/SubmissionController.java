package com.coded.coded_server.controller;

import com.coded.coded_server.dto.SubmissionRequestDto;
import com.coded.coded_server.dto.ChallengeResponseDto;
import com.coded.coded_server.dto.SubmissionCreateResponseDto;
import com.coded.coded_server.dto.SubmissionResponseDto;
import com.coded.coded_server.jwt.JwtService;
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
public class SubmissionController {

    private final SubmissionService submissionService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<SubmissionCreateResponseDto> createSubmission(@RequestBody SubmissionRequestDto dto) {
        SubmissionCreateResponseDto response = submissionService.createSubmission(dto);
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/challenge/{challengeId}")
    public ResponseEntity<List<SubmissionResponseDto>> getSubmissionsByChallengeId(@PathVariable UUID challengeId) {
        List<SubmissionResponseDto> responses = submissionService.getSubmissionsByChallengeId(challengeId);
        return ResponseEntity.ok(responses);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/user")
    public ResponseEntity<List<SubmissionResponseDto>> getUserSubmissions(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        String userId = jwtService.extractId(token);

        List<SubmissionResponseDto> responses = submissionService.getSubmissionsByUserId(UUID.fromString(userId));
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/student/{challengeId}/{studentId}/{submissionKey}")
    public ResponseEntity<SubmissionResponseDto> getSubmissionByStudentId(@PathVariable String submissionKey,@PathVariable String studentId, @PathVariable UUID challengeId) {
        SubmissionResponseDto response = submissionService.getSubmissionsByStudentId(studentId, challengeId, submissionKey);
        return ResponseEntity.ok(response);
    }
}
