package com.coded.coded_server.repository;

import com.coded.coded_server.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChallengeRepository extends JpaRepository<Challenge, UUID> {
    List<Challenge> findByUserId(UUID userId);
}
