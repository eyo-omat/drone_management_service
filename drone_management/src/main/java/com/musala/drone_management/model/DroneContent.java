package com.musala.drone_management.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "tbl_drone_content")
@NoArgsConstructor
public class DroneContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long droneContentId;

    @OneToOne()
    @JoinColumn(name = "droneId")
    private Drone drone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicationId")
    @ToString.Exclude
    private List<Medication> medications;
}
