package com.coded.coded_server.mapper;

import com.coded.coded_server.dto.ChallengeRequestDto;
import com.coded.coded_server.dto.ChallengeResponseDto;
import com.coded.coded_server.dto.ChallengeSingleResponseDto;
import com.coded.coded_server.model.Challenge;
import com.coded.coded_server.model.User;

import java.util.UUID;

public class ChallengeMapper {

    public static Challenge toEntity(ChallengeRequestDto dto, User user) {
        return Challenge.builder()
                .id(UUID.randomUUID())
                .user(user)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .time(dto.getTime())
                .languages(dto.getLanguages())
                .build();
    }

    public static ChallengeResponseDto toResponse(Challenge challenge) {
        ChallengeResponseDto response = new ChallengeResponseDto();
        response.setId(challenge.getId());
        response.setUserId(challenge.getUser().getId());
        response.setTitle(challenge.getTitle());
        response.setDescription(challenge.getDescription());
        response.setTime(challenge.getTime());
        response.setLanguages(challenge.getLanguages());
        return response;
    }

    public static ChallengeSingleResponseDto toSingleResponse(Challenge challenge) {
        ChallengeSingleResponseDto response = new ChallengeSingleResponseDto();
        response.setId(challenge.getId());
        response.setUser(UserMapper.toResponse(challenge.getUser()));
        response.setTitle(challenge.getTitle());
        response.setDescription(challenge.getDescription());
        response.setTime(challenge.getTime());
        response.setLanguages(challenge.getLanguages());
        return response;
    }
}
