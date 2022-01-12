package com.nice.order.center.common;

public enum YesOrNoEnum {


    YES(1, "是"),

    NO(0, "否");

    private Integer code;

    private String remark;

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