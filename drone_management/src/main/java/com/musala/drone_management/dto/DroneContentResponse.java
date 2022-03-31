package com.musala.drone_management.dto;

import com.musala.drone_management.model.Medication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DroneContentResponse {

    private long droneId;
    private Double currentWeight;
    private List<Medication> medications;
}
