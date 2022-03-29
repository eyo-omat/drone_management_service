package com.musala.drone_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/drone-manager")
public class DispatchController {

    @PostMapping("/register")
    public ResponseEntity<String> registerDrone(@Valid @RequestBody String payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body("registered drone");
    }

    @PostMapping("/load")
    public ResponseEntity<String> loadDrone(@Valid @RequestBody String payload) {
        return ResponseEntity.status(HttpStatus.OK).body("Drone loaded");
    }

    @GetMapping("/contents/{droneId}")
    public ResponseEntity<List<String>> fetchDroneContents(@Valid @PathVariable long droneId) {
        return ResponseEntity.status(HttpStatus.OK).body(List.of("medication1", "medication2"));
    }

    @GetMapping("/available-drones")
    public ResponseEntity<List<String>> fetchAvailableDrones() {
        return ResponseEntity.status(HttpStatus.OK).body(List.of("drones", "drones"));
    }

    @GetMapping("/battery/{droneId}")
    public ResponseEntity<String> fetchDroneBattery(@PathVariable long droneId) {
        return ResponseEntity.status(HttpStatus.OK).body("Drone percentage");
    }
}
