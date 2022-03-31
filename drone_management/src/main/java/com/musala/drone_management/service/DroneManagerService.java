package com.musala.drone_management.service;

import com.musala.drone_management.dto.*;
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
import java.util.stream.Collectors;

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
        drone.setCurrentWeight(0.0);
        drone.setWeightLimit(droneRequest.getWeightLimit());
        drone.setBatteryCapacity(droneRequest.getBatteryCapacity());
        drone.setState(droneRequest.getState());
        droneRepository.save(drone);
    }

    public LoadDroneResponse loadDrone(LoadDroneRequest loadDroneRequest) {
        // Fetch drone to be loaded
        Drone drone = droneRepository.findDroneByDroneIdAndCurrentWeightLessThanWeightLimitAndStateIn(
                loadDroneRequest.getDroneId(),
                List.of(StateEnum.IDLE.name(), StateEnum.LOADING.name())
        );

        if (drone == null) {
            throw new RuntimeException("Drone with droneId: " + loadDroneRequest.getDroneId() + " not found");
        }

        double totalLoadWeight = loadDroneRequest.getMedicationRequests().stream()
                .mapToDouble(Medication::getWeight)
                .sum();

        if ((totalLoadWeight + drone.getCurrentWeight()) > drone.getWeightLimit()) {
            throw new RuntimeException("Drone is beyond capacity");
        }

        DroneContent droneContent = new DroneContent();
        droneContent.setDrone(drone);
        droneContent.setMedications(loadDroneRequest.getMedicationRequests());

        droneContentRepository.save(droneContent);

        //update drone state after loading
        double newDroneWeight = totalLoadWeight + drone.getCurrentWeight();
        drone.setState(newDroneWeight == drone.getWeightLimit() ? StateEnum.LOADED.name() : StateEnum.LOADING.name());
        droneRepository.save(drone);

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

    public List<DroneContentResponse> fetchDroneContents(long droneId) {
        droneRepository.findById(droneId).orElseThrow(
                () -> new RuntimeException("Drone with droneId: " + droneId + " not found")
        );

        return droneContentRepository.findDroneContentByDrone_DroneId(droneId).stream()
                .map(droneContent -> DroneContentResponse.builder()
                        .droneId(droneContent.getDrone().getDroneId())
                        .currentWeight(droneContent.getDrone().getCurrentWeight())
                        .medications(droneContent.getMedications())
                        .build()).collect(Collectors.toList());
    }
}
