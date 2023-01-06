package com.nice.order.center.common.exception;

/**
 * 错误码
 *
 * @author hai.huang.a@outlook.com
 * @date 2023/1/6 13:17
 */
public interface IErrorCode {

    /**
     * 错误码
     *
     * @return String
     */
    String getErrorCode();

    /**
     * 错误信息
     *
     * @return String
     */
    String getErrorMessage();

}
