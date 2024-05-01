package com.gp2.appwebeventsmanagementgp2.validation;

import com.gp2.appwebeventsmanagementgp2.dto.EventDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartEndDateValidator implements ConstraintValidator<StartEndDateCheck, EventDto> {
    @Override
    public boolean isValid(EventDto event, ConstraintValidatorContext context) {
        return event.getStartDate().isBefore(event.getEndDate());
    }
}

