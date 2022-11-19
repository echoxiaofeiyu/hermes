package com.fish.hermes.service.api.impl.action;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fish.hermes.common.enums.RespStatusEnum;
import com.fish.hermes.common.vo.BasicResultVO;
import com.fish.hermes.service.api.enums.BusinessCode;
import com.fish.hermes.service.api.impl.domain.SendTaskModel;
import com.fish.hermes.support.mq.SendMqService;
import com.fish.hermes.support.pipeline.BusinessProcess;
import com.fish.hermes.support.pipeline.ProcessContext;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 将消息发送到MQ
 * @Author :  shenzhenxing
 * @Date :  2022/9/27 19:49
 */
@Service
@Slf4j
public class SendMqAction implements BusinessProcess<SendTaskModel> {

    @Resource
    private SendMqService sendMqService;

    @Value("${hermes.mq.pipeline}")
    private String mqPipeline;

    @Value("${hermes.business.topic.name}")
    private String sendMessageTopic;

    @Value("${hermes.business.recall.topic.name}")
    private String austinRecall;

    @Value("${hermes.business.tagId.value}")
    private String tagId;


    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();

        try {
            if (BusinessCode.COMMON_SEND.getCode().equals(context.getCode())) {
                String message = JSON.toJSONString(sendTaskModel.getTaskInfo(), SerializerFeature.WriteClassName);
                sendMqService.send(sendMessageTopic, message, tagId);
            } else if (BusinessCode.RECALL.getCode().equals(context.getCode())) {
                String message = JSON.toJSONString(sendTaskModel.getMessageTemplate(),
                        SerializerFeature.WriteClassName);
                sendMqService.send(austinRecall, message, tagId);
            }
        } catch (Exception e) {
            context.setNeedBreak(Boolean.TRUE).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("send {} fail! e: {} , params: {}", mqPipeline, Throwables.getStackTraceAsString(e),
                    JSON.toJSONString(CollUtil.getFirst(sendTaskModel.getTaskInfo().listIterator())));
        }

    }
}
