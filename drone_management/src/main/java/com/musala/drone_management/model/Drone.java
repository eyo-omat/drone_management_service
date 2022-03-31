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
@Table(name = "drone")
@NoArgsConstructor
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long droneId;

    String serial;

    String model;

    Double currentWeight;

    Double weightLimit;

    Double batteryCapacity;

    String state;
}
