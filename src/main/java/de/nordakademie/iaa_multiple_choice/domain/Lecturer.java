package de.nordakademie.iaa_multiple_choice.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("lecturer")
public class Lecturer extends User {

    public Lecturer() {
    }

    public Lecturer(String firstName, String lastName, String email, String hashedPassword) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(hashedPassword);
    }
}
