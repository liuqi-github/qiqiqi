package com.thread.demo.enums;

import lombok.Getter;

/**
 * @author LiuQi
 * @date 2020/11/26 10:26
 */
@Getter
public enum MessageTypeEnum {
    TRANSFER_OUT(1, "转入"),

    TRANSFER_IN(0, "转出");

    private Integer value;
    private String memo;

    MessageTypeEnum(Integer value, String memo) {
        this.value = value;
        this.memo = memo;
    }
}
