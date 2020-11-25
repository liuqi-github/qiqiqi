package com.thread.demo.enums;

import lombok.Getter;

/**
 * @author LiuQi
 * @date 2020/11/25 18:23
 */
@Getter
public enum MessageEnum {
    FAIL_SEND(2, "发送失败"),

    SUCCESS_SEND(1, "已发送"),

    NOT_SEND(0, "待发送");


    private Integer value;
    private String memo;

    MessageEnum(Integer value, String memo) {
        this.value = value;
        this.memo = memo;
    }
}
