package com.fish.hermes.service.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Description:
 * @Author :  shenzhenxing
 * @Date :  2022/9/24 10:18
 */
@Getter
@ToString
@AllArgsConstructor
public enum BusinessCode {

    /** 普通发送流程 */
    COMMON_SEND("send", "普通发送"),

    /** 撤回流程 */
    RECALL("recall", "撤回消息");

    /**
     * code
     */
    private final String code;

    /**
     * 类型说明
     */
    private final String description;
}
