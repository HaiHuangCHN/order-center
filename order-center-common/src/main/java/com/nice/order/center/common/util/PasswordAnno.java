package com.nice.order.center.common.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Inherited
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { PasswordAnnoValidator.class })
public @interface PasswordAnno {


    /**
     * Message
     * 
     * @return
     */
    String message() default "error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
