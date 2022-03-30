package com.musala.drone_management.repository;

import com.musala.drone_management.model.DroneContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneContentRepository extends JpaRepository<DroneContent, Long> {
}
