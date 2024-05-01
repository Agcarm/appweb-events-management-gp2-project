package com.gp2.appwebeventsmanagementgp2.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StartEndDateValidator.class)
public @interface StartEndDateCheck {
    String message() default "Start date should not be after the end date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


