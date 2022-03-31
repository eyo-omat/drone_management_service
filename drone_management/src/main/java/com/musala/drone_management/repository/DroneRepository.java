package com.musala.drone_management.repository;

import com.musala.drone_management.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    @Query("SELECT D FROM Drone D WHERE D.droneId = :droneId and D.currentWeight < D.weightLimit and D.state in :states")
    Drone findDroneByDroneIdAndCurrentWeightLessThanWeightLimitAndStateIn(
            @Param("droneId") long droneId,
            @Param("states") List<String> states
    );
}
