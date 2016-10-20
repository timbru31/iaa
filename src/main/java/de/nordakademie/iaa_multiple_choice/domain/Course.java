package de.nordakademie.iaa_multiple_choice.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    @Column(name = "field_of_study")
    private String fieldOfStudy;

    @NaturalId
    private int number;

    @Basic
    private String lecturer;

    @Basic
    private String title;
}
