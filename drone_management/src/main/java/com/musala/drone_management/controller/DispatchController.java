package com.musala.drone_management.controller;

import com.musala.drone_management.dto.DroneContentResponse;
import com.musala.drone_management.dto.LoadDroneRequest;
import com.musala.drone_management.dto.LoadDroneResponse;
import com.musala.drone_management.dto.RegisterDroneRequest;
import com.musala.drone_management.model.Drone;
import com.musala.drone_management.service.DroneManagerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/drone-manager")
@RequiredArgsConstructor
public class DispatchController {

    @NonNull
    DroneManagerService managerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerDrone(@Valid @RequestBody RegisterDroneRequest droneRequest) {
        managerService.registerDrone(droneRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("registered drone");
    }

    @PostMapping("/load")
    public ResponseEntity<LoadDroneResponse> loadDrone(@Valid @RequestBody LoadDroneRequest loadDroneRequest) {
        LoadDroneResponse loadDroneResponse = managerService.loadDrone(loadDroneRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loadDroneResponse);
    }

    @GetMapping("/contents/{droneId}")
    public ResponseEntity<List<String>> fetchDroneContents(@Valid @PathVariable long droneId) {
        List<DroneContentResponse> droneContents = managerService.fetchDroneContents(droneId);
        return ResponseEntity.status(HttpStatus.OK).body(List.of("medication1", "medication2"));
    }

    @GetMapping("/available-drones")
    public ResponseEntity<List<String>> fetchAvailableDrones() {
        return ResponseEntity.status(HttpStatus.OK).body(List.of("drones", "drones"));
    }

    @GetMapping("/battery/{droneId}")
    public ResponseEntity<Drone> fetchDroneBattery(@PathVariable long droneId) {
        Drone droneResponse = managerService.fetchDroneBattery(droneId);
        return ResponseEntity.status(HttpStatus.OK).body(droneResponse);
    }
}
