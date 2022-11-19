package com.fish.hermes.handler.handler;

import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.support.entity.MessageTemplate;

/**
 * @Description: 消息处理器
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 14:39
 */
public interface Handler {

    /**
     * 处理器
     *
     * @param taskInfo
     */
    void doHandler(TaskInfo taskInfo);

    /**
     * 撤回消息
     *
     * @param messageTemplate
     * @return
     */
    void recall(MessageTemplate messageTemplate);

}
