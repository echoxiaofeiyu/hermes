package com.fish.hermes.service.api.impl.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.fish.hermes.support.pipeline.BusinessProcess;
import com.fish.hermes.support.pipeline.ProcessContext;
import com.fish.hermes.common.enums.RespStatusEnum;
import com.fish.hermes.common.vo.BasicResultVO;
import com.fish.hermes.service.api.domain.MessageParam;
import com.fish.hermes.service.api.impl.domain.SendTaskModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 前置参数校验
 * @Author :  shenzhenxing
 * @Date :  2022/9/27 19:41
 */
@Slf4j
public class PreParamCheckAction implements BusinessProcess {

    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();
        Long messageTemplateId = sendTaskModel.getMessageTemplateId();
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();
        if(messageTemplateId == null || CollUtil.isEmpty(messageParamList)){
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }
        List<MessageParam> resultMessageParamList = messageParamList.stream()
                .filter(t -> StrUtil.isNotBlank(t.getReceiver())).collect(Collectors.toList());
        if(CollUtil.isEmpty(resultMessageParamList)){
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

        sendTaskModel.setMessageParamList(resultMessageParamList);
    }
}
