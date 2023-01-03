package com.nice.order.center.common.enumeration;

public enum YesOrNoEnum {

    YES(1, "是"),

    NO(0, "否");

    private final Integer code;

    private final String remark;

    public Integer getCode() {
        return this.code;
    }

    public String getRemark() {
        return this.remark;
    }

    YesOrNoEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

}