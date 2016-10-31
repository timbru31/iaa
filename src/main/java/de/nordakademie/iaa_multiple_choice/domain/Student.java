package de.nordakademie.iaa_multiple_choice.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("student")
public class Student extends User {
    @Basic
    private Integer studentNumber;
    @Basic
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Exam> registeredExams;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String hashedPassword, String activationToken,
            Integer studentNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(hashedPassword);
        setActivationToken(activationToken);
        setStudentNumber(studentNumber);
    }

    public void addExam(Exam exam) {
        registeredExams.add(exam);
    }

    @Override
    public boolean equals(Object obj) {
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

    public void removeExam(Exam exam) {
        registeredExams.remove(exam);
    }
}
