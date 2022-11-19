package com.fish.hermes.handler.pending;

import com.dtp.core.thread.DtpExecutor;
import com.fish.hermes.handler.config.HandlerThreadPoolConfig;
import com.fish.hermes.handler.receiver.utils.GroupIdMappingUtils;
import com.fish.hermes.support.utils.ThreadPoolUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @Description: 存储 每种消息类型 与 TaskPending 的关系
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 15:01
 */
@Component
public class TaskPendingHolder {

    @Resource
    private ThreadPoolUtils threadPoolUtils;

    private Map<String, ExecutorService> taskPendingHolder = new HashMap<>(32);

    /**
     * 获取得到所有的groupId
     */
    private static List<String> groupIds = GroupIdMappingUtils.getAllGroupIds();

    /**
     * 给每个渠道，每种消息类型初始化一个线程池
     */
    @PostConstruct
    public void init() {
        /**
         * example ThreadPoolName:hermes.im.notice
         * 可以通过apollo配置：dynamic-tp-nacos-dtp.yml  动态修改线程池的信息
         */
        for (String groupId : groupIds) {
            DtpExecutor executor = HandlerThreadPoolConfig.getExecutor(groupId);

            threadPoolUtils.register(executor);

            taskPendingHolder.put(groupId, executor);
        }
    }
    /**
     * 得到对应的线程池
     *
     * @param groupId
     * @return
     */
    public ExecutorService route(String groupId) {
        return taskPendingHolder.get(groupId);
    }
}
