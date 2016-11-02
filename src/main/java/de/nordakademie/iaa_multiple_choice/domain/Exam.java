package de.nordakademie.iaa_multiple_choice.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

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
import javax.persistence.OrderBy;

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

    @Basic
    private LocalDate startDate;

    @Basic
    private LocalDate endDate;

    @Basic
    private Integer minPoints;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private Set<Question> questions;

    public void addParticipant(final Student student, final String generatedToken) {
        tokenList.put(student, generatedToken);
    }

    public void addQuestion(final Question question) {
        questions.add(question);
    }

    public void clearParticipants() {
        tokenList.clear();
    }

    public Date formatEndDate() {
        return Date.valueOf(endDate);
    }

    public Date formatStartDate() {
        return Date.valueOf(startDate);
    }

    public boolean isDueDated() {
        final LocalDate today = LocalDate.now();
        return !(today.isBefore(startDate) || today.isAfter(endDate));
    }

    public boolean isEditable() {
        return startDate.isAfter(LocalDate.now());
    }

    public void removeParticipant(final Student student) {
        tokenList.remove(student);
    }
}
