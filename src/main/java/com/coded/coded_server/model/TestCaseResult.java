package com.coded.coded_server.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "test_case_result")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCaseResult {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

    @Column(name = "input", nullable = false)
    private String input;

    @Column(name = "expected", nullable = false)
    private String expected;

    @Column(name = "output", nullable = false)
    private String output;

    private Boolean passed;

    private String executionTime;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;
}
