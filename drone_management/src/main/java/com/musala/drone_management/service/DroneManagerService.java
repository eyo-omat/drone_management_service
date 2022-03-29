package com.musala.drone_management.service;

import com.musala.drone_management.dto.RegisterDroneRequest;
import com.musala.drone_management.model.Drone;
import com.musala.drone_management.repository.DroneRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DroneManagerService {

    @NonNull DroneRepository droneRepository;

    public void registerDrone(RegisterDroneRequest droneRequest) {
        Drone drone = new Drone();
        drone.setSerial(droneRequest.getSerial());
        drone.setModel(droneRequest.getModel());
        drone.setWeightLimit(droneRequest.getWeightLimit());
        drone.setBatteryCapacity(droneRequest.getBatteryCapacity());
        drone.setState(droneRequest.getState());
        droneRepository.save(drone);
    }

}
