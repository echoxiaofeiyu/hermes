package com.fish.hermes.service.api.service;

import com.fish.hermes.service.api.domain.SendRequest;
import com.fish.hermes.service.api.domain.SendResponse;

/**
 * @Description: 发送接口
 * @Author :  shenzhenxing
 * @Date :  2022/9/24 9:58
 */
public interface SendService {

    /**
     * 单文案发送接口
     * send
     * @param sendRequest
     * @return com.fish.hermes.service.api.domain.SendResponse
     * @Author: shenzhenxing on 2022/9/24 10:23
     */
    SendResponse send(SendRequest sendRequest);

    /**
     * 多文案发送接口
     * batchSend
     * @param sendRequest
     * @return com.fish.hermes.service.api.domain.SendResponse
     * @Author: shenzhenxing on 2022/9/24 10:23
     */
    SendResponse batchSend(SendRequest sendRequest);

}
