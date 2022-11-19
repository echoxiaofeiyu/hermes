package com.fish.hermes.handler.shield;

import com.fish.hermes.common.domain.TaskInfo;

/**
 * @Description: 屏蔽服务
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 14:20
 */
public interface ShieldService {

    /**
     * 屏蔽消息
     * @param taskInfo
     */
    void shield(TaskInfo taskInfo);

}
