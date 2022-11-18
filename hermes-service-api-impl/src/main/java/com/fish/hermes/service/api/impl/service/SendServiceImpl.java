package com.fish.hermes.service.api.impl.service;

import cn.monitor4all.logRecord.annotation.OperationLog;
import com.fish.hermes.support.pipeline.ProcessContext;
import com.fish.hermes.support.pipeline.ProcessController;
import com.fish.hermes.common.vo.BasicResultVO;
import com.fish.hermes.service.api.domain.SendRequest;
import com.fish.hermes.service.api.domain.SendResponse;
import com.fish.hermes.service.api.impl.domain.SendTaskModel;
import com.fish.hermes.service.api.service.SendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @Description:
 * @Author :  shenzhenxing
 * @Date :  2022/9/24 10:00
 */
@Service
@RequiredArgsConstructor
public class SendServiceImpl implements SendService {

    private final ProcessController processController;

    @Override
    @OperationLog(bizType = "SendService#send", bizId = "#sendRequest.messageTemplateId", msg = "#sendRequest")
    public SendResponse send(SendRequest sendRequest) {
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .messageParamList(Collections.singletonList(sendRequest.getMessageParam()))
                .build();

        ProcessContext context = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success())
                .build();

        ProcessContext process = processController.process(context);
        return new SendResponse(process.getResponse().getStatus(),process.getResponse().getMsg());
    }

    @Override
    public SendResponse batchSend(SendRequest sendRequest) {
        return null;
    }
}
