package com.musala.drone_management.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Setter
@Getter
@ToString
@Table(name = "tbl_drone")
@NoArgsConstructor
public class Drone {

    @Id
    @SequenceGenerator(
            name = "drone_sequence",
            sequenceName = "drone_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drone_sequence")
    Long droneId;

    String serial;

    String model;

    Double currentWeight;

    Double weightLimit;

    Double batteryCapacity;

    String state;
}
