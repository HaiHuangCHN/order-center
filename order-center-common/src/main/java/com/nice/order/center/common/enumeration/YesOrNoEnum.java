package com.nice.order.center.common.enumeration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum YesOrNoEnum {


    YES(Boolean.TRUE, "是"),

    NO(Boolean.FALSE, "否");

    private final Boolean code;

    private final String remark;

}