package de.nordakademie.iaa_multiple_choice.domain;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private boolean passed;

    @Basic
    private LocalDateTime startTime;

    @Basic
    @ManyToOne(fetch = FetchType.EAGER)
    private Exam exam;

    @Basic
    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;

    // TODO: how to store the answers to a question
    // private Map<Question, Set<Answer>> answers;
}
