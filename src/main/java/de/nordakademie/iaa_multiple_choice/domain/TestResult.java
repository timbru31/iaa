package de.nordakademie.iaa_multiple_choice.domain;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;

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
    private int points;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "question")
    @Column(name = "answers")
    @CollectionTable(name = "testresult_submitted_answers", joinColumns = @JoinColumn(name = "id"))
    private Map<Question, TestResultAnswers> submittedAnswers;

    public void calculateFinalPoints() {
        int finalPoints = 0;
        for (final Entry<Question, TestResultAnswers> entry : submittedAnswers.entrySet()) {
            final Question question = entry.getKey();
            double partialPoint = 0;
            final double pointStep = (double) question.getPoints() / question.getAnswers().size();
            final Iterator<Answer> correctAnswers = question.getAnswers().iterator();
            final Iterator<Answer> studentAnswers = entry.getValue().getAnswers().iterator();
            while (correctAnswers.hasNext() && studentAnswers.hasNext()) {
                final Answer studentAnswer = studentAnswers.next();
                final Answer correctAnswer = correctAnswers.next();
                if (question.getType() == QuestionType.FILL_IN_THE_BLANK) {
                    if (studentAnswer.getText().trim().equalsIgnoreCase(correctAnswer.getText().trim())) {
                        partialPoint += pointStep;
                    } else {
                        if (exam.getEvaluationMethod() == WrongAnswerEvaluationMethod.SUBTRACTION) {
                            partialPoint -= pointStep;
                        }
                    }
                } else if (question.getType() == QuestionType.SINGLE_CHOICE) {
                    // A single choice brings only points if the ONE correct one is chosen, otherwise always zero points
                    if (studentAnswer.isRightAnswer() == correctAnswer.isRightAnswer()
                            && correctAnswer.isRightAnswer()) {
                        partialPoint += question.getPoints();
                        break;
                    } else if (correctAnswer.isRightAnswer()) {
                        partialPoint = 0;
                        break;
                    }
                } else if (question.getType() == QuestionType.MULTIPLE_CHOICE) {
                    if (studentAnswer.isRightAnswer() == correctAnswer.isRightAnswer()) {
                        partialPoint += pointStep;
                    } else {
                        if (exam.getEvaluationMethod() == WrongAnswerEvaluationMethod.SUBTRACTION) {
                            partialPoint -= pointStep;
                        }
                    }
                }
            }
            if (partialPoint < 0) {
                partialPoint = 0;
            }
            finalPoints += (int) Math.round(partialPoint);
        }
        points = finalPoints;
        passed = (finalPoints / exam.getMaxPoints()) * 100 >= exam.getMinPoints();
    }

    public boolean isExpired() {
        final LocalDateTime now = LocalDateTime.now().minusSeconds(10L);
        return now.isAfter(startTime.plusMinutes(exam.getExamTime())) || endTime != null;
    }
}
