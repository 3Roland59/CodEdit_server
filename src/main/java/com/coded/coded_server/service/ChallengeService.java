package com.coded.coded_server.service;

import com.coded.coded_server.dto.ChallengeRequestDto;
import com.coded.coded_server.dto.ChallengeResponseDto;
import com.coded.coded_server.dto.ChallengeSingleResponseDto;
import com.coded.coded_server.model.User;

import java.util.List;
import java.util.UUID;

public interface ChallengeService {
    ChallengeResponseDto createChallenge(ChallengeRequestDto dto, User user);
    List<ChallengeResponseDto> getAllChallenges();
    List<ChallengeResponseDto> getAllChallengesById(UUID userId);
    ChallengeSingleResponseDto getChallengeById(UUID id);
    ChallengeResponseDto updateChallenge(UUID userId, UUID id, ChallengeRequestDto dto);
    void deleteChallenge(UUID id);
}
