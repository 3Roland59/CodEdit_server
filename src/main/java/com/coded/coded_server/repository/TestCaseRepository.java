package com.coded.coded_server.repository;

import com.coded.coded_server.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TestCaseRepository extends JpaRepository<TestCase, UUID> {
    List<TestCase> findByChallengeId(UUID challengeId);
}
