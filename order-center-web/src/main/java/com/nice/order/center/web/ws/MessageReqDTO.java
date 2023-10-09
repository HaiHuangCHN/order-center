package com.nice.order.center.web.ws;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author haihuang95@zto.com
 * @date 2023/10/9 16:33
 */
@Getter
@Setter
public class MessageReqDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    private String sid;

    private String msg;


}
