package com.fish.hermes.support.pipeline;

import java.util.List;

/**
 * @Description: 业务执行模板(把责任链的逻辑串起来)
 * @Author :  shenzhenxing
 * @Date :  2022/9/27 19:17
 */
public class ProcessTemplate {

    private List<BusinessProcess> processList;

    public List<BusinessProcess> getProcessList() {
        return processList;
    }

    public void setProcessList(List<BusinessProcess> processList) {
        this.processList = processList;
    }
}
