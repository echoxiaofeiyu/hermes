package com.fish.hermes.support.utils;

import cn.monitor4all.logRecord.bean.LogDTO;
import cn.monitor4all.logRecord.service.IOperationLogGetService;
import com.alibaba.fastjson.JSON;
import com.fish.hermes.common.domain.AnchorInfo;
import com.fish.hermes.common.domain.LogParam;
import com.fish.hermes.support.mq.SendMqService;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 所有的日志都存在
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 13:20
 */
@Slf4j
@Component
public class LogUtils implements IOperationLogGetService {

    @Autowired
    private SendMqService sendMqService;

    @Value("${hermes.business.log.topic.name}")
    private String topicName;

    /**
     * 方法切面的日志 @OperationLog 所产生
     * createLog
     * @param logDTO
     * @return boolean
     * @Author: shenzhenxing on 2022/11/19 13:22
     */
    @Override
    public boolean createLog(LogDTO logDTO) {
        log.info("TestCustomLogListener 本地接收到日志 [{}]", logDTO);
        return true;
    }

    /**
     * 记录当前对象信息
     */
    public void print(LogParam logParam) {
        logParam.setTimestamp(System.currentTimeMillis());
        log.info(JSON.toJSONString(logParam));
    }

    /**
     * 记录打点信息
     */
    public void print(AnchorInfo anchorInfo) {
        anchorInfo.setTimestamp(System.currentTimeMillis());
        String message = JSON.toJSONString(anchorInfo);
        log.info(message);

        try {
            sendMqService.send(topicName, message);
        } catch (Exception e) {
            log.error("LogUtils#print send mq fail! e:{},params:{}", Throwables.getStackTraceAsString(e)
                    , JSON.toJSONString(anchorInfo));
        }
    }

    /**
     * 记录当前对象信息和打点信息
     */
    public void print(LogParam logParam, AnchorInfo anchorInfo) {
        print(anchorInfo);
        print(logParam);
    }

}
