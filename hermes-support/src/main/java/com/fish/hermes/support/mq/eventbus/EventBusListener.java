package com.fish.hermes.support.mq.eventbus;

import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.support.entity.MessageTemplate;

import java.util.List;

/**
 * @Description: 监听器
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 11:11
 */
public interface EventBusListener {

    /**
     * 消费消息
     * @param lists
     */
    void consume(List<TaskInfo> lists);

    /**
     * 撤回消息
     * @param messageTemplate
     */
    void recall(MessageTemplate messageTemplate);

}
