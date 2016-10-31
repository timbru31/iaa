package de.nordakademie.iaa_multiple_choice.domain;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

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

    @ManyToOne(optional = false)
    @NaturalId
    private Exam examId;

    @Basic
    private String text;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Answer> answers;

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public Set<Answer> getCorrectAnswers() {
        return answers.stream().filter(Answer::isRightAnswer).collect(Collectors.toSet());
    }
}
