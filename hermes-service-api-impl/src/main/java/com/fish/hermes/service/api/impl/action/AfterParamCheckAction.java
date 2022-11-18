package com.fish.hermes.service.api.impl.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.fish.hermes.support.pipeline.BusinessProcess;
import com.fish.hermes.support.pipeline.ProcessContext;
import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.common.enums.ChannelType;
import com.fish.hermes.common.enums.IdType;
import com.fish.hermes.common.enums.RespStatusEnum;
import com.fish.hermes.common.vo.BasicResultVO;
import com.fish.hermes.service.api.impl.domain.SendTaskModel;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 后置参数检查
 * @Author :  shenzhenxing
 * @Date :  2022/9/27 19:49
 */
@Slf4j
public class AfterParamCheckAction implements BusinessProcess {

    public static final String PHONE_REGEX_EXP = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[0-9])|(18[0-9])|(19[1,8,9]))\\d{8}$";

    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();
        List<TaskInfo> taskInfo = sendTaskModel.getTaskInfo();
        //1. 过滤不合法的手机号
        filterIllegalPhoneNumber(taskInfo);

        //2.
        if(CollUtil.isEmpty(taskInfo)){
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
        }

    }

    private void filterIllegalPhoneNumber(List<TaskInfo> taskInfo) {
        Integer idType = CollUtil.getFirst(taskInfo.iterator()).getIdType();
        Integer sendChannel = CollUtil.getFirst(taskInfo.iterator()).getSendChannel();
        if (IdType.PHONE.getCode().equals(idType) && ChannelType.SMS.getCode().equals(sendChannel)) {
            Iterator<TaskInfo> iterator = taskInfo.iterator();
            // 利用正则找出不合法的手机号
            while (iterator.hasNext()) {
                TaskInfo task = iterator.next();
                Set<String> illegalPhone = task.getReceiver().stream()
                        .filter(phone -> !ReUtil.isMatch(PHONE_REGEX_EXP, phone))
                        .collect(Collectors.toSet());
                if (CollUtil.isNotEmpty(illegalPhone)) {
                    task.getReceiver().removeAll(illegalPhone);
                    log.error("messageTemplateId:{} find illegal phone!{}", task.getMessageTemplateId(), JSON.toJSONString(illegalPhone));
                }
                if (CollUtil.isEmpty(task.getReceiver())) {
                    iterator.remove();
                }
            }
        }

    }
}
