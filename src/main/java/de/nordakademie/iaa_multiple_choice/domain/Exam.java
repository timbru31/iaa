package de.nordakademie.iaa_multiple_choice.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_id")
    private Long id;

    @Basic
    private HashMap<Integer, String> keyList;

    @Basic
    private String name;

    @Basic
    private Double creditPoints;

    @Basic
    private Integer examTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date finalSubmitDate;

    @Basic
    private Integer minPoints;

    @Basic
    private Boolean editable;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Question> questions;

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
