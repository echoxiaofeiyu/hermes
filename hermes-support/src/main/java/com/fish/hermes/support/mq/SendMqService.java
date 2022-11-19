package com.fish.hermes.support.mq;

/**
 * @Description: 发送数据至消息队列,可插拔式MQ
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 10:54
 */
public interface SendMqService {

    /**
     * 发送消息
     *
     * @param topic
     * @param jsonValue
     * @param tagId
     */
    void send(String topic, String jsonValue, String tagId);


    /**
     * 发送消息
     *
     * @param topic
     * @param jsonValue
     */
    void send(String topic, String jsonValue);

}
