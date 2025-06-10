package com.coded.coded_server.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coded.coded_server.model.TestCaseResult;


public interface TestCaseResultRepo extends JpaRepository<TestCaseResult, UUID> {
    List<TestCaseResult> findBySubmissionId(UUID submissionId);
}
