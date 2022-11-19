package com.fish.hermes.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 简单的埋点信息
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 13:24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleAnchorInfo {

    /**
     * 具体点位
     */
    private int state;

    /**
     * 业务Id(数据追踪使用)
     * 生成逻辑参考 TaskInfoUtils
     */
    private Long businessId;

    /**
     * 生成时间
     */
    private long timestamp;
}
