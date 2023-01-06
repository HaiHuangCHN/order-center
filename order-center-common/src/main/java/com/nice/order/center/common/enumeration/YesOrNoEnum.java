package com.nice.order.center.common.enumeration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum YesOrNoEnum {

    YES((byte)1, "是"),

    NO((byte)0, "否");

    private final Byte code;

    private final String remark;

}