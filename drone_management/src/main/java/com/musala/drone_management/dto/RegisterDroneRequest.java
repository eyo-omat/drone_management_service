package com.musala.drone_management.dto;

import com.musala.drone_management.validators.DroneStateValidator;
import com.musala.drone_management.validators.ModelValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDroneRequest {

    @NotBlank(message = "Serial number cannot be blank")
    @Size(max = 100, message = "Serial number can have a maximum of 100 characters")
    String serial;

    @NotBlank(message = "Serial number cannot be blank")
    @ModelValidator(
            enumClazz = ModelEum.class,
            message = "Invalid model provided"
    )
    String model;

    @Max(value = 500, message = "A drone can carry a maximum of 500gr")
    Double weightLimit;

    @Max(value = 100)
    Double batteryCapacity;

    @DroneStateValidator(
            enumClazz = StateEnum.class,
            message = "Invalid drone state provided"
    )
    String state;
}
