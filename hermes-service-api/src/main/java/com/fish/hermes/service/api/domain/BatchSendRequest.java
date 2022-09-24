package com.fish.hermes.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 发送接口的参数（批量）
 * @Author :  shenzhenxing
 * @Date :  2022/9/24 10:08
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchSendRequest {

    /**
     * 执行业务类型
     * 必传,参考 BusinessCode枚举
     */
    private String code;

    /**
     * 消息模板Id
     * 必传
     */
    private Long messageTemplateId;

    /**
     * 消息相关的参数
     * 必传
     */
    private List<MessageParam> messageParamList;
}
