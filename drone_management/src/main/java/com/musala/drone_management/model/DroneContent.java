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
    @SequenceGenerator(
            name = "drone_content_sequence",
            sequenceName = "drone_content_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drone_content_sequence")
    private Long droneContentId;

    @OneToOne()
    @JoinColumn(name = "droneId")
    private Drone drone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "droneContentId")
    @ToString.Exclude
    private List<Medication> medications;
}
