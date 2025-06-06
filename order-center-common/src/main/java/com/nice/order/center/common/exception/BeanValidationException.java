package com.nice.order.center.common.exception;

import lombok.Getter;
import org.springframework.validation.Errors;

/**
 * 验证异常
 *
 * @author hai.huang.a@outlook.com
 * @date 2023/1/6 13:17
 */
public class BeanValidationException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_BEAN_VALIDATION_EXCEPTION_ERROR_CODE = "99999";

    public static final String DEFAULT_BEAN_VALIDATION_EXCEPTION_ERROR_MESSAGE = "bean validation error!";

    /**
     * The errors which contains the filed name and the error message
     */
    @Getter
    private final Errors errors;

    public BeanValidationException(Errors errors) {
        super(DEFAULT_BEAN_VALIDATION_EXCEPTION_ERROR_CODE, DEFAULT_BEAN_VALIDATION_EXCEPTION_ERROR_MESSAGE);
        this.errors = errors;
    }

}
