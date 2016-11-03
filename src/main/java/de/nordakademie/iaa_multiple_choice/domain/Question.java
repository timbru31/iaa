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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Answer> answers;

    public void addAnswer(final Answer answer) {
        answers.add(answer);
    }

    public Set<Answer> getCorrectAnswers() {
        return answers.stream().filter(Answer::isRightAnswer).collect(Collectors.toSet());
    }
}
