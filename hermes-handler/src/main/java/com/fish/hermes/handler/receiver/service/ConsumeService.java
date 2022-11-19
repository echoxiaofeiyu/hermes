package com.fish.hermes.handler.receiver.service;

import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.support.entity.MessageTemplate;

import java.util.List;

/**
 * @Description: 消息消费服务
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 11:23
 */
public interface ConsumeService {

    /**
     * 普通消息消费
     * consume2Send
     * @param taskInfoList
     * @return void
     * @Author: shenzhenxing on 2022/11/19 11:24
     */
    void consume2Send(List<TaskInfo> taskInfoList);

    /**
     * 撤回消息服务
     * consume2Recall
     * @param messageTemplate
     * @return void
     * @Author: shenzhenxing on 2022/11/19 11:25
     */
    void consume2Recall(MessageTemplate messageTemplate);

}
