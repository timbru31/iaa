package de.nordakademie.iaa_multiple_choice.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "student")
    @Column(name = "token")
    @CollectionTable(name = "student_exam_token_list", joinColumns = @JoinColumn(name = "id"))
    private Map<Student, String> tokenList;

    @Basic
    private String name;

    @Basic
    private Double creditPoints;

    @Basic
    private Integer examTime;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date finalSubmitDate;

    @Basic
    private Integer minPoints;

    @Basic
    private boolean editable;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Question> questions;

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void addStudent(Student student, String generatedToken) {
        tokenList.put(student, generatedToken);
    }

}
