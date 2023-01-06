package com.nice.order.center.common.exception;

import com.google.common.base.Joiner;
import lombok.Getter;

/**
 * 业务异常
 *
 * @author haihuang95@zto.com
 * @date 2023/1/6 13:17
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * The error code to identify exception
     */
    @Getter
    protected final String errorCode;

    private static final Joiner JOINER = Joiner.on(", ").skipNulls();

    public BusinessException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public BusinessException(IErrorCode error) {
        this(error.getErrorCode(), error.getErrorMessage());
    }

    public BusinessException(IErrorCode error, String extendMessage) {
        this(error.getErrorCode(), JOINER.join(error.getErrorMessage(), extendMessage));
    }

    public BusinessException(IErrorCode error, String... extendMessages) {
        this(error.getErrorCode(), JOINER.join(error.getErrorMessage(), JOINER.join(extendMessages)));
    }

    public BusinessException(String errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
    }

}
