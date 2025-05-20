package com.coded.coded_server.controller;

import com.coded.coded_server.dto.ChallengeRequestDto;
import com.coded.coded_server.dto.ChallengeResponseDto;
import com.coded.coded_server.dto.ChallengeSingleResponseDto;
import com.coded.coded_server.jwt.JwtService;
import com.coded.coded_server.model.User;
import com.coded.coded_server.repository.UserRepository;
import com.coded.coded_server.service.ChallengeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ChallengeResponseDto> createChallenge(
            @RequestBody ChallengeRequestDto dto,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        String userId = jwtService.extractId(token);
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        ChallengeResponseDto response = challengeService.createChallenge(dto, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ChallengeResponseDto>> getAll() {
        return ResponseEntity.ok(challengeService.getAllChallenges());
    }

    @GetMapping("/user")
public ResponseEntity<List<ChallengeResponseDto>> getUserChallenges(
        @RequestHeader("Authorization") String authHeader) {

    String token = authHeader.substring(7);
    String userId = jwtService.extractId(token);

    List<ChallengeResponseDto> challenges = challengeService.getAllChallengesById(UUID.fromString(userId));
    return ResponseEntity.ok(challenges);
}

    @GetMapping("/{id}")
    public ResponseEntity<ChallengeSingleResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(challengeService.getChallengeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChallengeResponseDto> updateChallenge(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody ChallengeRequestDto dto,
            @PathVariable UUID id
    ) {
        String token = authHeader.substring(7);
        UUID userId = UUID.fromString(jwtService.extractId(token));

        return ResponseEntity.ok(challengeService.updateChallenge(userId, id, dto));
    }


    @DeleteMapping("/{id}")
    public void deleteChallenge(@PathVariable UUID id) {
        challengeService.deleteChallenge(id);
    }
}
