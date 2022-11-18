package com.fish.hermes.support.pipeline;

/**
 * @Description: 业务执行器
 * @Author :  shenzhenxing
 * @Date :  2022/9/27 19:18
 */
public interface BusinessProcess {

    /**
     * 真正处理逻辑
     * @param context
     */
    void process(ProcessContext context);

}
