package com.musala.drone_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationRequest {

    @Pattern(regexp = "([A-Za-z0-9\\-_]+)")
    private String name; // (allowed only letters, numbers, ‘-‘, ‘_’);
    private Double weight;

    @Pattern(regexp = "([A-Z0-9_]+)")
    private String code; //(allowed only upper case letters, underscore and numbers);
    private String image; // (picture of the medication case).
}
