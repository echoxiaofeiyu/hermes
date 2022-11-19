package com.fish.hermes.support.mq.eventbus;

import com.alibaba.fastjson2.JSON;
import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.support.constants.MessageQueuePipeline;
import com.fish.hermes.support.entity.MessageTemplate;
import com.fish.hermes.support.mq.SendMqService;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Description: 事件总线
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 11:07
 */
@Service
@Slf4j
@ConditionalOnProperty(name = "hermes.mq.pipeline", havingValue = MessageQueuePipeline.EVENT_BUS)
public class EventBusSendMqServiceImpl implements SendMqService {

    private final EventBus eventBus = new EventBus();

    @Resource
    private EventBusListener eventBusListener;

    @Value("${hermes.business.topic.name}")
    private String sendTopic;

    @Value("${hermes.business.recall.topic.name}")
    private String recallTopic;

    @Override
    public void send(String topic, String jsonValue, String tagId) {
        eventBus.register(eventBusListener);
        if (Objects.equals(sendTopic, topic)) {
            eventBus.post(JSON.parseArray(jsonValue, TaskInfo.class));
        } else if (Objects.equals(recallTopic, topic)) {
            eventBus.post(JSON.parseArray(jsonValue, MessageTemplate.class));
        }
    }

    @Override
    public void send(String topic, String jsonValue) {
        send(topic, jsonValue, null);
    }
}
