package de.nordakademie.iaa_multiple_choice.domain;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("lecturer")
public class Lecturer extends User {
    @OneToMany(fetch = FetchType.EAGER)
    @OrderBy("startDate ASC")
    private Set<Exam> exams;

    public Lecturer() {
    }

    public Lecturer(final String firstName, final String lastName, final String email, final String hashedPassword,
            final String activationToken) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(hashedPassword);
        setActivationToken(activationToken);
    }

    public void addExam(final Exam exam) {
        exams.add(exam);
    }
}
