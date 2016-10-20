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
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    private String building;

    @NaturalId
    private String number;

    @Basic
    private int seats;

    @Basic
    @Column(name = "presenter_available")
    private boolean presenterAvailable;
}
