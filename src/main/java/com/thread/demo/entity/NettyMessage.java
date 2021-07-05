package com.thread.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LiuQi
 * @date 2021/1/20 16:31
 */
@Data
public class NettyMessage implements Serializable {
    //客户端名称
    String name;

    //消息
    String msg;

    //状态
    String status;

    @Override
    public String toString() {
        return "NettyMessage{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
