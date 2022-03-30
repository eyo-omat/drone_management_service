package com.musala.drone_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadDroneResponse {

    private Long droneId;
    private Double droneWeightLimit;
    private Double droneBatteryCapacity;
    private List<MedicationRequest> medicationRequests;
}
