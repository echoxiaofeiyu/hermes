package com.fish.hermes.handler.pending;

import cn.hutool.core.collection.CollUtil;
import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.handler.deduplication.DeduplicationRuleService;
import com.fish.hermes.handler.discard.DiscardMessageService;
import com.fish.hermes.handler.handler.HandlerHolder;
import com.fish.hermes.handler.shield.ShieldService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Task 执行器
 * 0.丢弃消息
 * 2.屏蔽消息
 * 2.通用去重功能
 * 3.发送消息
 * @Description:
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 13:44
 */
@Data
@Accessors(chain = true)
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Task implements Runnable{

    @Resource
    private DiscardMessageService discardMessageService;

    @Resource
    private ShieldService shieldService;

    @Resource
    private DeduplicationRuleService deduplicationRuleService;

    @Resource
    private HandlerHolder handlerHolder;

    private TaskInfo taskInfo;


    @Override
    public void run() {
        // 0. 丢弃消息
        if (discardMessageService.isDiscard(taskInfo)) {
            return;
        }
        // 1. 屏蔽消息
        shieldService.shield(taskInfo);

        // 2.平台通用去重
        if (CollUtil.isNotEmpty(taskInfo.getReceiver())) {
            //TODO 消息去重
            //deduplicationRuleService.duplication(taskInfo);
        }

        // 3. 真正发送消息
        if (CollUtil.isNotEmpty(taskInfo.getReceiver())) {
            handlerHolder.route(taskInfo.getSendChannel()).doHandler(taskInfo);
        }
    }
}
