package de.nordakademie.iaa_multiple_choice.domain;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Question entity.
 *
 * @author Tim Brust
 */
@Getter
@Setter
@Entity
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private Integer points;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Basic
    private String text;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC")
    private Set<Answer> answers;

    /**
     * Adds an answer.
     * 
     * @param answer the answer to add
     */
    public void addAnswer(final Answer answer) {
        answers.add(answer);
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        final Question question = (Question) obj;
        if (!id.equals(question.id) || !points.equals(question.points) || type != question.type
                || !text.equals(question.text) || answers.size() != question.answers.size()) {
            return false;
        }
        return true;
    }

    /**
     * Calculates a Set of correct answers.
     *
     * @return the Set of correct answers
     */
    public Set<Answer> getCorrectAnswers() {
        return answers.stream().filter(Answer::isRightAnswer).collect(Collectors.toSet());
    }

    /**
     * Formats a question text correctly, replacing [gap text] with [].
     *
     * @return the formatted question text
     */
    public String getFormattedQuestionText() {
        if (type == QuestionType.FILL_IN_THE_BLANK) {
            return text.replaceAll("\\[(.+?)\\]", "[]");
        }
        return text;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id == null ? 0 : id.hashCode());
        result = 31 * result + (text == null ? 0 : text.hashCode());
        result = 31 * result + (points == null ? 0 : points.hashCode());
        result = 31 * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    /**
     * Removes an answer from the list
     *
     * @param answer the answer to remove
     */
    public void removeAnswer(final Answer answer) {
        answers.remove(answer);
    }
}
