package com.nice.order.center.common.exception;

/**
 * 错误码
 *
 * @author haihuang95@zto.com
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
