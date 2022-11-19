package com.fish.hermes.support.utils;

import com.dtp.core.DtpRegistry;
import com.dtp.core.thread.DtpExecutor;
import com.fish.hermes.support.config.ThreadPoolExecutorShutdownDefinition;
import org.springframework.stereotype.Component;

/**
 * @Description: 线程池工具类
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 15:02
 */
@Component
public class ThreadPoolUtils {

    private ThreadPoolExecutorShutdownDefinition shutdownDefinition;

    private static final String SOURCE_NAME = "hermes";


    /**
     * 1. 将当前线程池 加入到 动态线程池内
     * 2. 注册 线程池 被Spring管理，优雅关闭
     */
    public void register(DtpExecutor dtpExecutor) {
        DtpRegistry.registerDtp(dtpExecutor, SOURCE_NAME);
        shutdownDefinition.registryExecutor(dtpExecutor);
    }

}
