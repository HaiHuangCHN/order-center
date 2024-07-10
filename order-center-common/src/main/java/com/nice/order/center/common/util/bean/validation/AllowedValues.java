package com.nice.order.center.common.util.bean.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Bean validation annotation to check allowed values
 */
@Documented
@Inherited
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { AllowedValuesValidator.class })
public @interface AllowedValues {


    /**
     * Message
     * 
     * @return
     */
    String message() default "error";

    /**
     * Set possible values
     * 
     * @return
     */
    String allowedValues();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
