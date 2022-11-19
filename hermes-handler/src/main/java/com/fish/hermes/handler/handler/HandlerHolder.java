package com.fish.hermes.handler.handler;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: channel->Handler的映射关系
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 14:39
 */
@Component
public class HandlerHolder {

    private Map<Integer, Handler> handlers = new HashMap<Integer, Handler>(128);

    public void putHandler(Integer channelCode, Handler handler) {
        handlers.put(channelCode, handler);
    }

    public Handler route(Integer channelCode) {
        return handlers.get(channelCode);
    }
}
