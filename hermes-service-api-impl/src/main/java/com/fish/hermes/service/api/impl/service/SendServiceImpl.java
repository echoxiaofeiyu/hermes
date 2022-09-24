package com.fish.hermes.service.api.impl.service;

import cn.monitor4all.logRecord.annotation.OperationLog;
import com.fish.hermes.service.api.domain.SendRequest;
import com.fish.hermes.service.api.domain.SendResponse;
import com.fish.hermes.service.api.service.SendService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author :  shenzhenxing
 * @Date :  2022/9/24 10:00
 */
@Service
public class SendServiceImpl implements SendService {


    @Override
    @OperationLog(bizType = "SendService#send", bizId = "#sendRequest.messageTemplateId", msg = "#sendRequest")
    public SendResponse send(SendRequest sendRequest) {
        return null;
    }

    @Override
    public SendResponse batchSend(SendRequest sendRequest) {
        return null;
    }
}
