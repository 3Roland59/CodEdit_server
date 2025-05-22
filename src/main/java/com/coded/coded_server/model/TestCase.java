package com.coded.coded_server.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "test_cases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCase {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Column(name = "input_data_type", nullable = false)
    private String inputDataType;

    @Column(name = "input_value", nullable = false, columnDefinition = "TEXT")
    private String inputValue;

    @Column(name = "output_data_type", nullable = false)
    private String outputDataType;

    @Column(name = "output_value", nullable = false, columnDefinition = "TEXT")
    private String outputValue;
}
