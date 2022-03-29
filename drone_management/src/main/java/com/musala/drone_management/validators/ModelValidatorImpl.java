package com.musala.drone_management.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ModelValidatorImpl implements ConstraintValidator<ModelValidator, String> {

    List<String> valueList = null;

    @Override
    public void initialize(ModelValidator constraintAnnotation) {
        valueList = new ArrayList<>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();

        @SuppressWarnings("rawtypes")
        Enum[] modelValArr = enumClass.getEnumConstants();

        for (@SuppressWarnings("rawtypes") Enum modelVal : modelValArr) {
            valueList.add(modelVal.toString());
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return valueList.contains(s);
    }
}
