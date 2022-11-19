package com.fish.hermes.handler.receiver.eventbus;

import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.support.constants.MessageQueuePipeline;
import com.fish.hermes.support.entity.MessageTemplate;
import com.fish.hermes.support.mq.eventbus.EventBusListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 11:22
 */
@Component
@ConditionalOnProperty(name = "austin.mq.pipeline", havingValue = MessageQueuePipeline.EVENT_BUS)
public class EventBusReceiver implements EventBusListener {

    @Override
    public void consume(List<TaskInfo> lists) {

    }

    @Override
    public void recall(MessageTemplate messageTemplate) {

    }
}
