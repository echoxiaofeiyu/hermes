package com.fish.hermes.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description: 发送接口的参数(单个消息)
 * @Author :  shenzhenxing
 * @Date :  2022/9/24 10:14
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendRequest {

    /**
     * 执行业务类型(默认填写 "send")
     */
    private String code;

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 消息相关的参数
     */
    private MessageParam messageParam;
}
