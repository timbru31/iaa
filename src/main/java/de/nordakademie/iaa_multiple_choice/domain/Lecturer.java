package de.nordakademie.iaa_multiple_choice.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("lecturer")
public class Lecturer extends User {

}
