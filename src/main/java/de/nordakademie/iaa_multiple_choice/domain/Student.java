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

@Getter
@Setter
@Entity
@DiscriminatorValue("student")
@ToString(exclude = "testResults")
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
    private Set<TestResult> testResults;

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

    public void addExam(final Exam exam) {
        registeredExams.add(exam);
    }

    public void addTestResult(final TestResult testResult) {
        testResults.add(testResult);
    }

    @Override
    public boolean equals(final Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        final Student student = (Student) obj;
        if (student.studentNumber == null && studentNumber != null
                || studentNumber.intValue() != student.studentNumber.intValue()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (studentNumber == null ? 0 : studentNumber.hashCode());
        return result;
    }

    public boolean hasTakenExam(final Exam exam) {
        return testResults.stream().anyMatch(ts -> exam.equals(ts.getExam()));
    }

    public void removeExam(final Exam exam) {
        registeredExams.remove(exam);
    }
}
