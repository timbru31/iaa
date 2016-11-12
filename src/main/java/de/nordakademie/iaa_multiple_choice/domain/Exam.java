package de.nordakademie.iaa_multiple_choice.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Exam entity.
 *
 * @author Tim Brust
 */
@Getter
@Setter
@Entity
@ToString(exclude = { "tokenList", "examResults" })
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "student")
    @Column(name = "token")
    @CollectionTable(name = "student_exam_token_list", joinColumns = @JoinColumn(name = "id"))
    private Map<Student, String> tokenList;

    @Basic
    private String name;

    @Enumerated(EnumType.STRING)
    private CreditPointType creditPoints;

    @Basic
    private Integer examTime;

    @Basic
    private LocalDate startDate;

    @Basic
    private LocalDate endDate;

    @Basic
    private Integer minPoints;

    @Enumerated(EnumType.STRING)
    private WrongAnswerEvaluationMethod evaluationMethod;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private Set<Question> questions;

    @Basic
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ExamResult> examResults;

    public void addExamResult(final ExamResult examResult) {
        examResults.add(examResult);
    }

    /**
     * Adds a student and a generated token to the tokenList Map.
     *
     * @param student
     *            the student to add
     * @param generatedToken
     *            the generated token
     */
    public void addParticipant(final Student student, final String generatedToken) {
        tokenList.put(student, generatedToken);
    }

    /**
     * Adds a question to the Set of questions.
     *
     * @param question
     *            the question to add.
     */
    public void addQuestion(final Question question) {
        questions.add(question);
    }

    /**
     * Clears the participant list.
     */
    public void clearParticipants() {
        tokenList.clear();
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        final Exam exam = (Exam) obj;
        if (id != exam.id || !name.equals(exam.name) || examTime != exam.examTime || minPoints != exam.minPoints
                || creditPoints != exam.creditPoints || !startDate.isEqual(exam.startDate)
                || !endDate.isEqual(exam.endDate) || evaluationMethod != exam.evaluationMethod
                || questions.size() != exam.questions.size() || tokenList.size() != exam.tokenList.size()
                || examResults.size() != exam.examResults.size()) {
            return false;
        }
        return true;
    }

    /**
     * Formats the LocalDate endDate to a java.sql.Date.
     *
     * @return the formatted sql date
     */
    public Date formatEndDate() {
        return Date.valueOf(endDate);
    }

    /**
     * Formats the LocalDate startDate to a java.sql.Date.
     *
     * @return the formatted sql date
     */
    public Date formatStartDate() {
        return Date.valueOf(startDate);
    }

    /**
     * Returns the first question.
     *
     * @return the question or null if not found
     */
    public Question getFirstQuestion() {
        try {
            return questions.iterator().next();
        } catch (final NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Calculates the maximum points for the exam.
     *
     * @return the maximal points
     */
    public int getMaxPoints() {
        return questions.stream().mapToInt(Question::getPoints).sum();
    }

    /**
     * Searches the next question based on a given question.
     *
     * @param question
     *            the question to find the next one after
     * @return the next question or null if they question was the last
     */
    public Question getNextQuestion(final Question question) {
        boolean foundSelf = false;
        for (final Question q : questions) {
            if (foundSelf) {
                return q;
            }
            if (q.equals(question)) {
                foundSelf = true;
            }
        }
        return null;
    }

    /**
     * Searches the previous question based on a given question.
     *
     * @param question
     *            the question to find the previous one
     * @return the previous question or null if they question was the first
     */
    public Question getPreviousQuestion(final Question question) {
        Question predecessor = null;
        for (final Question q : questions) {
            if (q.equals(question)) {
                return predecessor;
            }
            predecessor = q;
        }
        return null;
    }

    /**
     * Returns the token for a student.
     *
     * @param student
     *            the student to retrieve the token for
     * @return the token
     */
    public String getToken(final Student student) {
        return tokenList.get(student);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id == null ? 0 : id.hashCode());
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (minPoints == null ? 0 : minPoints.hashCode());
        result = 31 * result + (examTime == null ? 0 : examTime.hashCode());
        result = 31 * result + (startDate == null ? 0 : startDate.hashCode());
        result = 31 * result + (endDate == null ? 0 : endDate.hashCode());
        result = 31 * result + (evaluationMethod == null ? 0 : evaluationMethod.hashCode());
        result = 31 * result + (creditPoints == null ? 0 : creditPoints.hashCode());
        return result;
    }

    /**
     * Checks if the student is a participant of the exam.
     *
     * @param student
     *            the student to check
     * @return true if he is a participant, otherwise false
     */
    public boolean hasParticipant(final Student student) {
        return tokenList.containsKey(student);
    }

    /**
     * Checks if the exam has this question
     *
     * @param question
     *            the question to check
     * @return true if exam has this question, otherwise false
     */
    public boolean hasQuestion(final Question question) {
        return questions.contains(question);
    }

    /**
     * Checks if the exam has questions at all.
     *
     * @return true if the exam has questions, otherwise false
     */
    public boolean hasQuestions() {
        return questions != null && !questions.isEmpty();
    }

    /**
     * Tests if the exam can be taken. The startDate must have been reached, but not the end date.
     *
     * @return true if the exam is takeable, otherwise false
     */
    public boolean isDueDated() {
        final LocalDate today = LocalDate.now();
        return !(today.isBefore(startDate) || today.isAfter(endDate));
    }

    /**
     * Checks if the exam is editable.
     *
     * @return true if it's editable, otherwise false
     */
    public boolean isEditable() {
        return startDate.isAfter(LocalDate.now());
    }

    /**
     * Checks if the given question is the first question in the Set.
     *
     * @param question
     *            the question to check
     * @return true if it's the first question, false otherwise
     */
    public boolean isFirstQuestion(final Question question) {
        int index = 0;
        for (final Question q : questions) {
            if (q.equals(question)) {
                return index == 0;
            }
            index++;
        }
        return false;
    }

    /**
     * Checks if the given question is the last question in the Set.
     *
     * @param question
     *            the question to check
     * @return true if it's the last question, false otherwise
     */
    public boolean isLastQuestion(final Question question) {
        int index = 0;
        final int size = questions.size() - 1;
        for (final Question q : questions) {
            if (q.equals(question)) {
                return index == size;
            }
            index++;
        }
        return false;
    }

    /**
     * Removes a participant from the exam.
     *
     * @param student
     *            the student to remove
     */
    public void removeParticipant(final Student student) {
        tokenList.remove(student);
    }

    /**
     * Removes a question from the exam.
     *
     * @param question
     *            the question to remove
     */
    public void removeQuestion(final Question question) {
        questions.remove(question);
    }
}
