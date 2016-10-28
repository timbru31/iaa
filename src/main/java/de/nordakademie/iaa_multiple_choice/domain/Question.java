package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Long id;

    @Basic
    private Double points;

    @Basic
    private QuestionType type;

    @Basic
    private String text;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Answer> answers;

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getCorrectAnswers() {
        return answers.stream().filter(Answer::isRightAnswer).collect(Collectors.toList());
    }
}
