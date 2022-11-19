package com.fish.hermes.service.api.impl.config;

import com.fish.hermes.service.api.enums.BusinessCode;
import com.fish.hermes.service.api.impl.action.AfterParamCheckAction;
import com.fish.hermes.service.api.impl.action.AssembleAction;
import com.fish.hermes.service.api.impl.action.PreParamCheckAction;
import com.fish.hermes.service.api.impl.action.SendMqAction;
import com.fish.hermes.support.pipeline.ProcessController;
import com.fish.hermes.support.pipeline.ProcessTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: pipeline配置类
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 10:32
 */
@Configuration
@RequiredArgsConstructor
public class PipelineConfig {

    private final PreParamCheckAction preParamCheckAction;

    private final AssembleAction assembleAction;

    private final AfterParamCheckAction afterParamCheckAction;

    private final SendMqAction sendMqAction;

    /**
     * 普通发送执行流程
     * 1. 前置参数校验
     * 2. 组装参数校验
     * 3. 后置参数校验
     * 4. 发送消息至MQ
     * commonSendTemplate
     * @return com.fish.hermes.support.pipeline.ProcessTemplate
     * @Author: shenzhenxing on 2022/11/19 10:37
     */
    @Bean("commonSendTemplate")
    public ProcessTemplate commonSendTemplate() {
        ProcessTemplate processTemplate = new ProcessTemplate();
        processTemplate.setProcessList(Arrays.asList(preParamCheckAction,assembleAction,
                afterParamCheckAction,sendMqAction));
        return processTemplate;
    }

    /**
     * 消息撤回模板
     * 1. 组装参数
     * 2. 发送MQ
     * recallMessageTemplate
     * @return com.fish.hermes.support.pipeline.ProcessTemplate
     * @Author: shenzhenxing on 2022/11/19 10:38
     */
    @Bean("recallMessageTemplate")
    public ProcessTemplate recallMessageTemplate(){
        ProcessTemplate processTemplate = new ProcessTemplate();
        processTemplate.setProcessList(Arrays.asList(assembleAction,sendMqAction));
        return processTemplate;
    }

    /**
     * pipeline流程控制器
     * processController
     * @return com.fish.hermes.support.pipeline.ProcessController
     * @Author: shenzhenxing on 2022/11/19 10:42
     */
    @Bean
    public ProcessController processController(){
        ProcessController processController = new ProcessController();
        Map<String, ProcessTemplate> templateConfig = new HashMap<>(4);
        templateConfig.put(BusinessCode.COMMON_SEND.getCode(),commonSendTemplate());
        templateConfig.put(BusinessCode.RECALL.getCode(), recallMessageTemplate());
        processController.setTemplateConfig(templateConfig);
        return processController;
    }



}
