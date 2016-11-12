package de.nordakademie.iaa_multiple_choice.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Student entity.
 *
 * @author Tim Brust
 */
@Getter
@Setter
@Entity
@DiscriminatorValue("student")
@ToString(exclude = "examResults", callSuper = true)
public class Student extends User {
    @Column(unique = true, nullable = false)
    private Integer studentNumber;
    @Basic
    @ManyToMany(fetch = FetchType.EAGER)
    @OrderBy("startDate ASC")
    private Set<Exam> registeredExams;

    @Basic
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ExamResult> examResults;

    public Student() {
    }

    public Student(final String firstName, final String lastName, final String email, final String hashedPassword,
            final String activationToken, final Integer studentNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(hashedPassword);
        setActivationToken(activationToken);
        setStudentNumber(studentNumber);
    }

    /**
     * Adds an exam.
     *
     * @param exam
     *            the exam to add
     */
    public void addExam(final Exam exam) {
        registeredExams.add(exam);
    }

    public void addExamResult(final ExamResult examResult) {
        examResults.add(examResult);
    }

    /**
     * Checks if the student can take any exams.
     *
     * @return true if there are registered but not taken exams, otherwise false
     */
    public boolean canTakeExams() {
        return registeredExams != null && registeredExams.stream().anyMatch(e -> !hasTakenExam(e));
    }

    @Override
    public boolean equals(final Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        final Student student = (Student) obj;
        if (student.studentNumber == null && studentNumber != null
                || student.studentNumber != null && studentNumber == null
                || !studentNumber.equals(student.studentNumber)) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the student has finished the exam.
     *
     * @param exam
     *            the exam to check
     * @return true if he finished the exam, false otherwise
     */
    public boolean hasFinishedExam(final Exam exam) {
        return examResults != null && examResults.stream().anyMatch(ts -> exam.equals(ts.getExam()) && ts.isExpired());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (studentNumber == null ? 0 : studentNumber.hashCode());
        return result;
    }

    /**
     * Checks if the student has taken the exam.
     *
     * @param exam
     *            the exam to check
     * @return true if he has taken the exam, false otherwise
     */
    public boolean hasTakenExam(final Exam exam) {
        return examResults != null && examResults.stream().anyMatch(ts -> exam.equals(ts.getExam()));
    }

    /**
     * Removes an exam.
     *
     * @param exam
     *            the exam to remove
     */
    public void removeExam(final Exam exam) {
        registeredExams.remove(exam);
    }
}
