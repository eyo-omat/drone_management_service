package com.musala.drone_management.dto;

import com.musala.drone_management.model.Medication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadDroneRequest {

    @NotNull
    private Long droneId;

    @Valid
    private List<Medication> medicationRequests;
}
