package com.musala.drone_management.repository;

import com.musala.drone_management.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    Drone findDroneByDroneIdAndStateIn(long droneId, List<String> states);
}
