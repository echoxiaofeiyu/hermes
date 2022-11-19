package com.fish.hermes.handler.receiver.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.fish.hermes.common.domain.AnchorInfo;
import com.fish.hermes.common.domain.LogParam;
import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.common.enums.AnchorState;
import com.fish.hermes.handler.pending.Task;
import com.fish.hermes.handler.pending.TaskPendingHolder;
import com.fish.hermes.handler.receiver.service.ConsumeService;
import com.fish.hermes.handler.receiver.utils.GroupIdMappingUtils;
import com.fish.hermes.support.entity.MessageTemplate;
import com.fish.hermes.support.utils.LogUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 11:25
 */
@Service
public class ConsumeServiceImpl implements ConsumeService {

    private static final String LOG_BIZ_TYPE = "Receiver#consumer";
    private static final String LOG_BIZ_RECALL_TYPE = "Receiver#recall";

    @Resource
    private ApplicationContext context;

    @Resource
    private TaskPendingHolder taskPendingHolder;


    @Resource
    private LogUtils logUtils;


    @Override
    public void consume2Send(List<TaskInfo> taskInfoList) {
        String topicGroupId = GroupIdMappingUtils.getGroupIdByTaskInfo(CollUtil.getFirst(taskInfoList.iterator()));
        for (TaskInfo taskInfo : taskInfoList) {
            logUtils.print(LogParam.builder().bizType(LOG_BIZ_TYPE).object(taskInfo).build(),
                    AnchorInfo.builder().ids(taskInfo.getReceiver()).businessId(taskInfo.getBusinessId()).state(AnchorState.RECEIVE.getCode()).build());
            Task task = context.getBean(Task.class).setTaskInfo(taskInfo);
            taskPendingHolder.route(topicGroupId).execute(task);
        }
    }

    @Override
    public void consume2Recall(MessageTemplate messageTemplate) {

    }
}
