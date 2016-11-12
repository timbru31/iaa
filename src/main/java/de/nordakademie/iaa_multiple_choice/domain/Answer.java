package de.nordakademie.iaa_multiple_choice.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Jens Gottwald defines answers
 */
@Getter
@Setter
@Entity
@ToString
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private boolean rightAnswer;

    @Basic
    private String text;

    public Answer() {
    }

    public Answer(final String text, final boolean rightAnswer) {
        this.text = text;
        this.rightAnswer = rightAnswer;
    }
}
