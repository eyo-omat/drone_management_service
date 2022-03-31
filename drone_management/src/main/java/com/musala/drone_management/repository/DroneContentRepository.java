package com.musala.drone_management.repository;

import com.musala.drone_management.model.DroneContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneContentRepository extends JpaRepository<DroneContent, Long> {

    List<DroneContent> findDroneContentByDrone_DroneId(long droneId);
}
