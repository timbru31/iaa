package de.nordakademie.iaa_multiple_choice.domain;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@Getter
@Setter
@Entity
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Long id;

    @Basic
    private Double points;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Basic
    private String text;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private Set<Answer> answers;

    public void addAnswer(final Answer answer) {
        answers.add(answer);
    }

    public Set<Answer> getCorrectAnswers() {
        return answers.stream().filter(Answer::isRightAnswer).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        Question question = (Question) obj;
        if (!id.equals(question.id) || !points.equals(question.points) || type != question.type
                || !text.equals(question.text) || answers.size() != question.answers.size()) {
            return false;
        }
        return true;
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
}
