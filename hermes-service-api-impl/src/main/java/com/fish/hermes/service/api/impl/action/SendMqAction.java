package com.fish.hermes.service.api.impl.action;

import com.fish.hermes.service.api.impl.domain.SendTaskModel;
import com.fish.hermes.support.pipeline.BusinessProcess;
import com.fish.hermes.support.pipeline.ProcessContext;

/**
 * @Description: 将消息发送到MQ
 * @Author :  shenzhenxing
 * @Date :  2022/9/27 19:49
 */
public class SendMqAction implements BusinessProcess<SendTaskModel> {

    @Override
    public void process(ProcessContext<SendTaskModel> context) {

    }
}
