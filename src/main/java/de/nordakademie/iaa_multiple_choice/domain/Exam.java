package de.nordakademie.iaa_multiple_choice.domain;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}
