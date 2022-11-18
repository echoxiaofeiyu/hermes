package com.fish.hermes.support.exception;

import com.fish.hermes.support.pipeline.ProcessContext;
import com.fish.hermes.common.enums.RespStatusEnum;

/**
 * @Description:
 * @Author :  shenzhenxing
 * @Date :  2022/11/18 14:39
 */
public class ProcessException extends RuntimeException {

    /**
     * 流程处理上下文
     */
    private final ProcessContext processContext;

    public ProcessException(ProcessContext processContext) {
        super();
        this.processContext = processContext;
    }

    public ProcessException(ProcessContext processContext, Throwable cause) {
        super(cause);
        this.processContext = processContext;
    }

    @Override
    public String getMessage() {
        if(this.processContext != null){
            return this.processContext.getResponse().getMsg();
        } else {
            return RespStatusEnum.CONTEXT_IS_NULL.getMsg();
        }
    }

    public ProcessContext getProcessContext() {
        return processContext;
    }
}
