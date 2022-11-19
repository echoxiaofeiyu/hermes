package com.fish.hermes.handler.receiver.utils;

import cn.hutool.core.text.StrPool;
import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.common.enums.ChannelType;
import com.fish.hermes.common.enums.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: groupId 标识着每一个消费者组
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 13:26
 */
public class GroupIdMappingUtils {

    /**
     * 获取所有的groupIds
     * (不同的渠道不同的消息类型拥有自己的groupId)
     */
    public static List<String> getAllGroupIds(){
        List<String> groupIds = new ArrayList<>();
        for (ChannelType channelType : ChannelType.values()) {
            for (MessageType messageType : MessageType.values()) {
                groupIds.add(channelType.getCodeEn() + StrPool.DOT + messageType.getCode());
            }
        }
        return groupIds;
    }

    /**
     * 根据TaskInfo获取当前消息的groupId
     * getGroupIdByTaskInfo
     * @param taskInfo
     * @return java.lang.String
     * @Author: shenzhenxing on 2022/11/19 13:34
     */
    public static String getGroupIdByTaskInfo(TaskInfo taskInfo) {
        String channelType = ChannelType.getEnumByCode(taskInfo.getSendChannel()).getCodeEn();
        String messageType = MessageType.getEnumByCode(taskInfo.getMsgType()).getCodeEn();
        return channelType + StrPool.DOT + messageType;
    }

}
