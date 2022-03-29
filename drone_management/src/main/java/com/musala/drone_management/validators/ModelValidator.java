package com.musala.drone_management.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ModelValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotNull(message = "Value cannot be null")
@ReportAsSingleViolation
public @interface ModelValidator {

    Class<? extends Enum<?>> enumClazz();

    String message() default "Model is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
