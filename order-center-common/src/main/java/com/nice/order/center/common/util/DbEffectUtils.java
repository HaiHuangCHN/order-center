package com.nice.order.center.common.util;

import com.nice.order.center.common.exception.BusinessException;
import com.nice.order.center.common.exception.ErrorCode;
import com.nice.order.center.common.exception.IErrorCode;

/**
 * DB影响判断工具
 *
 * <p>注意使用在能够正常处理{@link BusinessException}的地方</p>
 *
 * @author haihuang95@zto.com
 * @date 2023/1/6 13:17
 */
public final class DbEffectUtils {

    private DbEffectUtils() {
        throw new UnsupportedOperationException("utility class...");
    }

    public static void checkEffect(int expectedRows, int effectRows, IErrorCode errorCode) {
        if (expectedRows != effectRows) {
            throw new BusinessException(errorCode);
        }
    }

    public static void checkEffect(boolean assertExpression, IErrorCode errorCode) {
        if (!assertExpression) {
            throw new BusinessException(errorCode);
        }
    }

    public static void checkInsert(int expectedRows, int effectRows) {
        if (expectedRows != effectRows) {
            throw new BusinessException(ErrorCode.DB_ERROR.INSERT_ERROR);
        }
    }

    public static void checkInsert(boolean assertExpression) {
        if (!assertExpression) {
            throw new BusinessException(ErrorCode.DB_ERROR.INSERT_ERROR);
        }
    }

    public static void checkUpdate(int expectedRows, int effectRows) {
        if (expectedRows != effectRows) {
            throw new BusinessException(ErrorCode.DB_ERROR.UPDATE_ERROR);
        }
    }

    public static void checkUpdate(boolean assertExpression) {
        if (!assertExpression) {
            throw new BusinessException(ErrorCode.DB_ERROR.UPDATE_ERROR);
        }
    }

    public static void checkDelete(int expectedRows, int effectRows) {
        if (expectedRows != effectRows) {
            throw new BusinessException(ErrorCode.DB_ERROR.DELETE_ERROR);
        }
    }

    public static void checkDelete(boolean assertExpression) {
        if (!assertExpression) {
            throw new BusinessException(ErrorCode.DB_ERROR.DELETE_ERROR);
        }
    }

}
