package de.nordakademie.iaa_multiple_choice.domain;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("student")
public class Student extends User {
    @Basic
    private Integer studentNumber;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String hashedPassword, Integer studentNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(hashedPassword);
        setStudentNumber(studentNumber);
    }
}
