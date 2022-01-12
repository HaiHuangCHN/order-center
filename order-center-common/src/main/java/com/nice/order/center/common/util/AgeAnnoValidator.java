package com.nice.order.center.common.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeAnnoValidator implements ConstraintValidator<AgeAnno, Integer> {


    private static final Integer DEFAULT_AGE = new Integer(18);

    @Override
    public void initialize(AgeAnno constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        if (age == null) {
            return false;
        }
        return age.compareTo(DEFAULT_AGE) > 0;
    }


}