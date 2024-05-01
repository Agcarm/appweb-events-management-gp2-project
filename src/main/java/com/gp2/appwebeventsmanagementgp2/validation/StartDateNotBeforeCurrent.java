package com.gp2.appwebeventsmanagementgp2.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.*;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StartDateNotBeforeCurrentValidator.class)
public @interface StartDateNotBeforeCurrent {
    String message() default "Start date cannot be before the current date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

