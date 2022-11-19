package com.fish.hermes.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Description: 屏蔽类型
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 14:24
 */
@Getter
@ToString
@AllArgsConstructor
public enum ShieldType {

    NIGHT_NO_SHIELD(10, "夜间不屏蔽"),
    NIGHT_SHIELD(20, "夜间屏蔽"),
    NIGHT_SHIELD_BUT_NEXT_DAY_SEND(30, "夜间屏蔽(次日早上9点发送)");

    private final Integer code;
    private final String description;
}
