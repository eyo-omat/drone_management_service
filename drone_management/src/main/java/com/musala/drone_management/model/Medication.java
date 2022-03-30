package com.musala.drone_management.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "tbl_employee")
@NoArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long medicationId;

    private String name; // (allowed only letters, numbers, ‘-‘, ‘_’);
    private Double weight;
    private String code; //(allowed only upper case letters, underscore and numbers);
    private String image; // (picture of the medication case).
}
