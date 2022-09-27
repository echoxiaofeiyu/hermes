package com.fish.heremes.support.pipeline;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.fish.hermes.common.enums.RespStatusEnum;
import com.fish.hermes.common.vo.BasicResultVO;

import java.util.List;
import java.util.Map;

/**
 * @Description: 流程控制器
 * @Author :  shenzhenxing
 * @Date :  2022/9/27 19:15
 */
public class ProcessController {

    private Map<String,ProcessTemplate> templateConfig = null;

    public ProcessContext process(ProcessContext context){
        if(!preCheck(context)){
            return context;
        }

        List<BusinessProcess> processList = templateConfig.get(context.getCode()).getProcessList();
        for (BusinessProcess businessProcess : processList) {
            businessProcess.process(context);
            if(context.getNeedBreak()){
                break;
            }
        }
        return context;
    }

    private Boolean preCheck(ProcessContext context) {
        //上下文
        if(context == null){
            context = new ProcessContext();
            context.setResponse(BasicResultVO.fail(RespStatusEnum.CONTEXT_IS_NULL));
            return false;
        }

        //业务代码校验
        String businessCode = context.getCode();
        if(StrUtil.isBlank(businessCode)){
            context.setResponse(BasicResultVO.fail(RespStatusEnum.BUSINESS_CODE_IS_NULL));
            return false;
        }

        //执行模板
        ProcessTemplate processTemplate = templateConfig.get(businessCode);
        if(processTemplate == null){
            context.setResponse(BasicResultVO.fail(RespStatusEnum.PROCESS_TEMPLATE_IS_NULL));
            return false;
        }

        //执行模板列表
        List<BusinessProcess> processList = processTemplate.getProcessList();
        if (CollUtil.isEmpty(processList)) {
            context.setResponse(BasicResultVO.fail(RespStatusEnum.PROCESS_LIST_IS_NULL));
            return false;
        }

        return true;
    }
}
