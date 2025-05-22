package com.coded.coded_server.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "submissions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Submission {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 10, nullable = false, unique = true)
    private String studentId;

    @Column(nullable = false)
    private String studentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Lob
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String language;

    @Column
    private String result; // e.g., "Success", "Failed", "Compile Error"

    @Column
    private Double score;

    @Column(columnDefinition = "TEXT")
    private String scoreBreakdown;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
