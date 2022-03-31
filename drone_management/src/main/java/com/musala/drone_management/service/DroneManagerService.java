package com.musala.drone_management.service;

import com.musala.drone_management.dto.LoadDroneRequest;
import com.musala.drone_management.dto.LoadDroneResponse;
import com.musala.drone_management.dto.RegisterDroneRequest;
import com.musala.drone_management.dto.StateEnum;
import com.musala.drone_management.model.Drone;
import com.musala.drone_management.model.DroneContent;
import com.musala.drone_management.model.Medication;
import com.musala.drone_management.repository.DroneContentRepository;
import com.musala.drone_management.repository.DroneRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneManagerService {

    @NonNull DroneRepository droneRepository;

    @NonNull
    DroneContentRepository droneContentRepository;

    public void registerDrone(RegisterDroneRequest droneRequest) {
        Drone drone = new Drone();
        drone.setSerial(droneRequest.getSerial());
        drone.setModel(droneRequest.getModel());
        drone.setWeightLimit(droneRequest.getWeightLimit());
        drone.setBatteryCapacity(droneRequest.getBatteryCapacity());
        drone.setState(droneRequest.getState());
        droneRepository.save(drone);
    }

    public LoadDroneResponse loadDrone(LoadDroneRequest loadDroneRequest) {
        // Fetch drone to be loaded
        Drone drone = droneRepository.findDroneByDroneIdAndStateIn(
                loadDroneRequest.getDroneId(),
                List.of(StateEnum.IDLE.name(), StateEnum.LOADING.name())
        );

        if (drone == null) {
            throw new RuntimeException("Drone with droneId: " + loadDroneRequest.getDroneId() + " not found");
        }

        double totalLoadWeight = loadDroneRequest.getMedicationRequests().stream()
                .mapToDouble(Medication::getWeight)
                .sum();

        if (totalLoadWeight > drone.getWeightLimit()) {
            throw new RuntimeException("Drone is beyond capacity");
        }

        DroneContent droneContent = new DroneContent();
        droneContent.setDrone(drone);
        droneContent.setMedications(loadDroneRequest.getMedicationRequests());

        droneContentRepository.save(droneContent);


        return LoadDroneResponse.builder()
                .droneId(drone.getDroneId())
                .droneWeightLimit(drone.getWeightLimit())
                .droneBatteryCapacity(drone.getBatteryCapacity())
                .medicationRequests(droneContent.getMedications())
                .build();
    }

    public Drone fetchDroneBattery(long droneId) {
        Optional<Drone> droneOptional = droneRepository.findById(droneId);
        if (droneOptional.isEmpty()) {
            throw new RuntimeException("drone with droneId: " + droneId + " does not exist");
        }

        return droneOptional.get();
    }
}
