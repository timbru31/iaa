package de.nordakademie.iaa_multiple_choice.domain;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ExamResult entity.
 *
 * @author Tim Brust
 */
@Getter
@Setter
@Entity
@ToString
public class ExamResult {
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
    private Exam exam;

    @Basic
    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "question")
    @Column(name = "answers")
    @CollectionTable(name = "examresult_submitted_answers", joinColumns = @JoinColumn(name = "id"))
    private Map<Question, ExamResultAnswers> submittedAnswers;

    /**
     * Calculates the final points, based on all answers. Note: not side effect free.
     */
    public void calculateFinalPoints() {
        int finalPoints = 0;
        for (final Entry<Question, ExamResultAnswers> entry : submittedAnswers.entrySet()) {
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
                    } else if (studentAnswer.isRightAnswer() && !correctAnswer.isRightAnswer()) {
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
        final int maxPoints = exam.getMaxPoints();
        passed = (finalPoints / (double) maxPoints) * 100 >= exam.getMinPoints();
    }

    /**
     * Finds a question in the submitted answers by it's id.
     *
     * @param questionId the question id to search for
     * @return the question if found, otherwise null
     */
    public Question findQuestionById(final Long questionId) {
        final Optional<Question> findFirst = getSubmittedAnswers().keySet().stream()
                .filter(q -> q.getId() == questionId).findFirst();
        if (findFirst.isPresent()) {
            return findFirst.get();
        }
        return null;
    }

    /**
     * Finds submitted answers by a question id.
     *
     * @param questionId the question id to search fo
     * @return the submtted answers if found, otherwise null
     */
    public ExamResultAnswers findSubmittedAnswersByQuestionId(final Long questionId) {
        for (final Entry<Question, ExamResultAnswers> entry : submittedAnswers.entrySet()) {
            final Question question = entry.getKey();
            if (question.getId() == questionId) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Checks if the examResult is expired. There is a goodwill of 10 seconds to balance e.g. latency.
     *
     * @return true if the exam time expired, false otherwise
     */
    public boolean isExpired() {
        final LocalDateTime now = LocalDateTime.now().minusSeconds(10L);
        return now.isAfter(startTime.plusMinutes(exam.getExamTime())) || endTime != null;
    }
}
