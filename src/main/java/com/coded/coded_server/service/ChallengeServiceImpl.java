package com.coded.coded_server.service;

import com.coded.coded_server.dto.ChallengeRequestDto;
import com.coded.coded_server.dto.ChallengeResponseDto;
import com.coded.coded_server.dto.ChallengeSingleResponseDto;
import com.coded.coded_server.exception.ResourceNotFoundException;
import com.coded.coded_server.mapper.ChallengeMapper;
import com.coded.coded_server.mapper.TestCaseMapper;
import com.coded.coded_server.model.Challenge;
import com.coded.coded_server.model.TestCase;
import com.coded.coded_server.model.User;
import com.coded.coded_server.repository.ChallengeRepository;
import com.coded.coded_server.repository.TestCaseRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final TestCaseRepository testCaseRepository;

    @Override
    public ChallengeResponseDto createChallenge(ChallengeRequestDto dto, User user) {
        Challenge challenge = ChallengeMapper.toEntity(dto, user);
        challenge.setId(UUID.randomUUID());
        Challenge savedChallenge = challengeRepository.save(challenge);


        List<TestCase> testCases = dto.getTestCases().stream()
                .map(testCaseDto -> TestCaseMapper.toEntity(testCaseDto, challenge))
                .collect(Collectors.toList());
        testCaseRepository.saveAll(testCases);

        savedChallenge.setTestCases(testCases);
        return ChallengeMapper.toResponse(savedChallenge);
    }


    @Override
    public List<ChallengeResponseDto> getAllChallenges() {
        return challengeRepository.findAll()
                .stream()
                .map(ChallengeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChallengeResponseDto> getAllChallengesById(UUID userId) {
        return challengeRepository.findByUserId(userId)
                .stream()
                .map(ChallengeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ChallengeSingleResponseDto getChallengeById(UUID id) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found"));
        return ChallengeMapper.toSingleResponse(challenge);
    }

    @Override
    public ChallengeResponseDto updateChallenge(UUID userId, UUID id, ChallengeRequestDto dto) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found"));

        if (!challenge.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized: You can only update your own challenges");
        }

        challenge.setTitle(dto.getTitle());
        challenge.setDescription(dto.getDescription());
        challenge.setLanguages(dto.getLanguages());
        challenge.setTime(dto.getTime());

        testCaseRepository.deleteAll(challenge.getTestCases());

        List<TestCase> updatedTestCases = dto.getTestCases().stream()
                .map(testCaseDto -> TestCaseMapper.toEntity(testCaseDto, challenge))
                .collect(Collectors.toList());

        testCaseRepository.saveAll(updatedTestCases);
        challenge.setTestCases(updatedTestCases);

        return ChallengeMapper.toResponse(challengeRepository.save(challenge));
    }



    @Override
    public void deleteChallenge(UUID id) {
        if (!challengeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Challenge not found");
        }
        challengeRepository.deleteById(id);
    }
} 
