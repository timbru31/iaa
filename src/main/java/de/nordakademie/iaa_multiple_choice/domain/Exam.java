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

@Getter
@Setter
@Entity
@ToString(exclude = { "tokenList", "testResults" })
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
    private Set<TestResult> testResults;

    public void addParticipant(final Student student, final String generatedToken) {
        tokenList.put(student, generatedToken);
    }

    public void addQuestion(final Question question) {
        questions.add(question);
    }

    public void addTestResult(final TestResult testResult) {
        testResults.add(testResult);
    }

    public void clearParticipants() {
        tokenList.clear();
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        final Exam exam = (Exam) obj;
        if (!id.equals(exam.id) || !name.equals(exam.name) || examTime.intValue() != exam.examTime.intValue()
                || minPoints.intValue() != exam.minPoints.intValue() || creditPoints != exam.creditPoints
                || !startDate.isEqual(exam.startDate) || !endDate.isEqual(exam.endDate)
                || evaluationMethod != exam.evaluationMethod || questions.size() != exam.questions.size()
                || tokenList.size() != exam.tokenList.size() || testResults.size() != exam.testResults.size()) {
            return false;
        }
        return true;
    }

    public Date formatEndDate() {
        return Date.valueOf(endDate);
    }

    public Date formatStartDate() {
        return Date.valueOf(startDate);
    }

    public Question getFirstQuestion() {
        try {
            return questions.iterator().next();
        } catch (final NoSuchElementException e) {
            return null;
        }
    }

    public int getMaxPoints() {
        return questions.stream().mapToInt(Question::getPoints).sum();
    }

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

    public boolean hasParticipant(final Student student) {
        return tokenList.containsKey(student);
    }

    public boolean hasQuestion(final Question question) {
        return questions.contains(question);
    }

    public boolean hasQuestions() {
        return questions != null && !questions.isEmpty();
    }

    public boolean isDueDated() {
        final LocalDate today = LocalDate.now();
        return !(today.isBefore(startDate) || today.isAfter(endDate));
    }

    public boolean isEditable() {
        return startDate.isAfter(LocalDate.now());
    }

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

    public void removeParticipant(final Student student) {
        tokenList.remove(student);
    }

    public void removeQuestion(final Question question) {
        questions.remove(question);
    }
}
