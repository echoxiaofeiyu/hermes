package com.fish.hermes.support.constants;

/**
 * @Description: 支持的消息队列类型
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 11:09
 */
public interface MessageQueuePipeline {
    String EVENT_BUS = "eventBus";

    String KAFKA = "kafka";

    String ROCKET_MQ = "rocketMq";

    String RABBIT_MQ = "rabbitMq";
}
