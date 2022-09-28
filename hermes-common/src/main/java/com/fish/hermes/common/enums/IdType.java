package com.fish.hermes.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Description: 发送ID类型枚举
 * @Author :  shenzhenxing
 * @Date :  2022/9/28 15:39
 */
@Getter
@ToString
@AllArgsConstructor
public enum IdType {
    USER_ID(10, "userId"),
    DID(20, "did"),
    PHONE(30, "phone"),
    OPEN_ID(40, "openId"),
    EMAIL(50, "email");


    private Integer code;
    private String description;
}
