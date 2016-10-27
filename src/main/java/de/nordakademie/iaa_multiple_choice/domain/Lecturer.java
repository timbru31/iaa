package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("lecturer")
public class Lecturer extends User {
    @OneToMany(fetch = FetchType.EAGER)
    private List<Exam> exams;

    public Lecturer() {
    }

    public Lecturer(String firstName, String lastName, String email, String hashedPassword) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(hashedPassword);
    }

    public void addExam(Exam exam) {
        exams.add(exam);
    }
}
