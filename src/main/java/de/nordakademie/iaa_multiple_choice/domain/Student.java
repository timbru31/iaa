package de.nordakademie.iaa_multiple_choice.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

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
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Exam> registeredExams;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String hashedPassword, Integer studentNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(hashedPassword);
        setStudentNumber(studentNumber);
    }

    public void addExam(Exam exam) {
        registeredExams.add(exam);
    }
}
