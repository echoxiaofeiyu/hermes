package com.fish.hermes.service.api.impl.domain;

import com.fish.heremes.support.pipeline.ProcessModel;
import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.service.api.domain.MessageParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 发送消息任务模型
 * @Author :  shenzhenxing
 * @Date :  2022/9/24 14:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTaskModel implements ProcessModel {

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 请求参数
     */
    private List<MessageParam> messageParamList;

    /**
     * 发送任务的信息
     */
    private List<TaskInfo> taskInfo;

}
