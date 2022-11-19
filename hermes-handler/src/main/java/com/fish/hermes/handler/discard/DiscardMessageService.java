package com.fish.hermes.handler.discard;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fish.hermes.common.constant.HermesConstant;
import com.fish.hermes.common.domain.AnchorInfo;
import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.common.enums.AnchorState;
import com.fish.hermes.support.service.ConfigService;
import com.fish.hermes.support.utils.LogUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 丢弃模板消息
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 14:05
 */
@Service
public class DiscardMessageService {
    private static final String DISCARD_MESSAGE_KEY = "discardMsgIds";

    @Resource
    private ConfigService config;

    @Resource
    private LogUtils logUtils;

    public boolean isDiscard(TaskInfo taskInfo){
        // 配置示例:	["1","2"]
        JSONArray array = JSON.parseArray(config.getProperty(DISCARD_MESSAGE_KEY,
                HermesConstant.CONFIG_CENTER_DEFAULT_VALUE_JSON_ARRAY));
        if (array.contains(String.valueOf(taskInfo.getMessageTemplateId()))) {
            logUtils.print(AnchorInfo.builder().businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver()).state(AnchorState.DISCARD.getCode()).build());
            return true;
        }
        return false;
    }

}
