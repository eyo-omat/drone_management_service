package com.musala.drone_management.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class DroneStateValidatorImpl implements ConstraintValidator<DroneStateValidator, String> {
    List<String> valueList = null;

    @Override
    public void initialize(DroneStateValidator constraintAnnotation) {
        valueList = new ArrayList<>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();

        @SuppressWarnings("rawtypes")
        Enum[] stateValArr = enumClass.getEnumConstants();

        for (@SuppressWarnings("rawtypes") Enum stateVal : stateValArr) {
            valueList.add(stateVal.toString().toUpperCase());
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return valueList.contains(s.toUpperCase());
    }
}
