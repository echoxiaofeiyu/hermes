package com.fish.hermes.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: 发送接口返回类
 * @Author :  shenzhenxing
 * @Date :  2022/9/24 10:01
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class SendResponse {

    /**
     * 响应状态
     */
    private String code;


    /**
     * 响应编码
     */
    private String msg;
}
