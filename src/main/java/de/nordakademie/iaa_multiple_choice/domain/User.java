package de.nordakademie.iaa_multiple_choice.domain;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User entity.
 *
 * @author Tim Brust
 */
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role")
@ToString
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @NaturalId
    private String email;

    @Basic
    private String password;

    @Basic
    private boolean activated;

    @Basic
    private String activationToken;

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        final User user = (User) obj;
        if (!id.equals(user.id) || !firstName.equals(user.firstName) || !lastName.equals(user.lastName)
                || !email.equals(user.email) || !password.equals(user.password) || activated != user.activated
                || !activationToken.equals(user.activationToken)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a concatenated name of fist and last name
     * 
     * @return the full name
     */
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id == null ? 0 : id.hashCode());
        result = 31 * result + (firstName == null ? 0 : firstName.hashCode());
        result = 31 * result + (lastName == null ? 0 : lastName.hashCode());
        result = 31 * result + (email == null ? 0 : email.hashCode());
        result = 31 * result + (password == null ? 0 : password.hashCode());
        result = 31 * result + (lastName == null ? 0 : lastName.hashCode());
        result = 31 * result + (activationToken == null ? 0 : activationToken.hashCode());
        result = 31 * result + (activated ? 1 : 0);
        return result;
    }
}
