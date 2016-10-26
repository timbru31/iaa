package de.nordakademie.iaa_multiple_choice.domain;

import java.util.HashMap;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private HashMap<Integer, String> keyList;

    @Basic
    private String name;

    @Basic
    private Double creditPoints;

    @Basic
    private Integer examTime;

    @Basic
    private Integer examPeriod;

    @Basic
    private Integer minPoints;

    @Basic
    private Boolean editable;

}
