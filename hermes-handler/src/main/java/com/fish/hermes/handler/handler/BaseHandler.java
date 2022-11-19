package com.fish.hermes.handler.handler;

import com.fish.hermes.common.domain.AnchorInfo;
import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.common.enums.AnchorState;
import com.fish.hermes.support.utils.LogUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Description:
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 14:42
 */
public abstract class BaseHandler implements Handler {

    @Resource
    private HandlerHolder handlerHolder;

    @Resource
    private LogUtils logUtils;

    /**
     * 标识渠道的Code
     * 子类初始化的时候指定
     */
    protected Integer channelCode;


    /**
     * 初始化渠道与Handler的映射关系
     */
    @PostConstruct
    private void init() {
        handlerHolder.putHandler(channelCode, this);
    }

    @Override
    public void doHandler(TaskInfo taskInfo) {
        //TODO 限流
        if(handler(taskInfo)){
            logUtils.print(AnchorInfo.builder().state(AnchorState.SEND_SUCCESS.getCode()).businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver()).build());
            return;
        }
        logUtils.print(AnchorInfo.builder().state(AnchorState.SEND_FAIL.getCode()).businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver()).build());
    }

    /**
     * 统一处理的handler接口
     *
     * @param taskInfo
     * @return
     */
    public abstract boolean handler(TaskInfo taskInfo);

}
