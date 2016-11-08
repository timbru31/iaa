package de.nordakademie.iaa_multiple_choice.domain;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private boolean passed;

    @Basic
    private LocalDateTime startTime;

    @Basic
    private LocalDateTime endTime;

    @Basic
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Exam exam;

    @Basic
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Student student;

    // TODO: how to store the answers to a question
    // private Map<Question, Set<Answer>> answers;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(startTime.plusMinutes(exam.getExamTime().intValue())) || endTime != null;
    }
}
