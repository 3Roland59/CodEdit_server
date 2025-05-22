package com.coded.coded_server.repository;

import com.coded.coded_server.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
    List<Submission> findByChallengeId(UUID challengeId);
    Optional<Submission> findByStudentId(String studentId);
}
