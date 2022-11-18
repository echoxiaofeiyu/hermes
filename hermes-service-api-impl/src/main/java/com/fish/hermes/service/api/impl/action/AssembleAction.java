package com.fish.hermes.service.api.impl.action;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.hermes.common.constant.HermesConstant;
import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.common.dto.ContentModel;
import com.fish.hermes.common.enums.ChannelType;
import com.fish.hermes.common.enums.RespStatusEnum;
import com.fish.hermes.common.vo.BasicResultVO;
import com.fish.hermes.service.api.domain.MessageParam;
import com.fish.hermes.service.api.enums.BusinessCode;
import com.fish.hermes.service.api.impl.domain.SendTaskModel;
import com.fish.hermes.support.entity.MessageTemplate;
import com.fish.hermes.support.mapper.MessageTemplateMapper;
import com.fish.hermes.support.pipeline.BusinessProcess;
import com.fish.hermes.support.pipeline.ProcessContext;
import com.fish.hermes.support.utils.ContentHolderUtil;
import com.fish.hermes.support.utils.TaskInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Description: 拼装参数
 * @Author :  shenzhenxing
 * @Date :  2022/9/27 19:48
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AssembleAction implements BusinessProcess<SendTaskModel> {

    private final MessageTemplateMapper messageTemplateMapper;

    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();
        Long messageTemplateId = sendTaskModel.getMessageTemplateId();

        MessageTemplate messageTemplate = messageTemplateMapper.selectById(messageTemplateId);
        if(messageTemplate == null || HermesConstant.TRUE.equals(messageTemplate.getIsDeleted())){
            context.setNeedBreak(Boolean.TRUE).setResponse(BasicResultVO.fail(RespStatusEnum.TEMPLATE_NOT_FOUND));
            return;
        }

        if(BusinessCode.COMMON_SEND.getCode().equals(context.getCode())){

        } else if (BusinessCode.RECALL.getCode().equals(context.getCode())) {
            sendTaskModel.setMessageTemplate(messageTemplate);
        }

    }

    private List<TaskInfo> assembleTaskInfo(SendTaskModel sendTaskModel, MessageTemplate messageTemplate){
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();
        List<TaskInfo> taskInfoList = new ArrayList<>();

        for (MessageParam messageParam : messageParamList) {
            TaskInfo taskInfo = TaskInfo.builder()
                    .messageTemplateId(messageTemplate.getId())
                    .businessId(TaskInfoUtils.generateBusinessId(messageTemplate.getId(), messageTemplate.getTemplateType()))
                    .receiver(new HashSet<>(Arrays.asList(messageParam.getReceiver().split(StrPool.COMMA))))
                    .idType(messageTemplate.getIdType())
                    .sendChannel(messageTemplate.getSendChannel())
                    .templateType(messageTemplate.getTemplateType())
                    .msgType(messageTemplate.getMsgType())
                    .shieldType(messageTemplate.getShieldType())
                    .sendAccount(messageTemplate.getSendAccount())
                    .contentModel(getContentModelValue(messageTemplate, messageParam))
                    .build();
            taskInfoList.add(taskInfo);
        }
        return taskInfoList;
    }

    /**
     * 获取 contentModel，替换模板msgContent中占位符信息
     */
    private static ContentModel getContentModelValue(MessageTemplate messageTemplate, MessageParam messageParam) {
        // 获取真正的ContentModel 类型
        Integer sendChannel = messageTemplate.getSendChannel();
        Class chanelModelClass = ChannelType.getChanelModelClassByCode(sendChannel);

        // 得到模板的 msgContent 和 入参
        Map<String, String> variables = messageParam.getVariables();
        JSONObject jsonObject = JSON.parseObject(messageTemplate.getMsgContent());

        //通过反射 组装出 contentModel
        Field[] fields = ReflectUtil.getFields(chanelModelClass);
        ContentModel contentModel = (ContentModel) ReflectUtil.newInstance(chanelModelClass);

        for (Field field : fields) {
            String originValue = jsonObject.getString(field.getName());

            if(StrUtil.isNotBlank(originValue)){
                String resultValue = ContentHolderUtil.replacePlaceHolder(originValue, variables);
                Object resultObj = JSONUtil.isTypeJSONObject(resultValue) ? JSONUtil.toBean(resultValue, field.getType()) :
                        resultValue;
                ReflectUtil.setFieldValue(contentModel, field, resultObj);
            }
        }

        // 如果 url 字段存在，则在url拼接对应的埋点参数
        String url = (String) ReflectUtil.getFieldValue(contentModel, "url");
        if (StrUtil.isNotBlank(url)) {
            String resultUrl = TaskInfoUtils.generateUrl(url, messageTemplate.getId(), messageTemplate.getTemplateType());
            ReflectUtil.setFieldValue(contentModel, "url", resultUrl);
        }
        return contentModel;

    }

}
