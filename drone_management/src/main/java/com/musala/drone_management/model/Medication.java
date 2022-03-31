package com.musala.drone_management.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@ToString
@Entity
@Table(name = "tbl_medication")
@NoArgsConstructor
public class Medication {

    @Id
    @SequenceGenerator(
            name = "medication_sequence",
            sequenceName = "medication_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medication_sequence")
    private Long medicationId;

    @Pattern(regexp = "([A-Za-z0-9\\-_]+)")
    private String name;
    private Double weight;

    @Pattern(regexp = "([A-Z0-9_]+)")
    private String code;
    private String image;
}
