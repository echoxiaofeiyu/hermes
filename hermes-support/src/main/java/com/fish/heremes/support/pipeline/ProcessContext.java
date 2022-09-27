package com.fish.heremes.support.pipeline;

import com.fish.hermes.common.vo.BasicResultVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description: 责任链上下文
 * @Author :  shenzhenxing
 * @Date :  2022/9/24 15:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ProcessContext {

    /**
     * 标识责任链的code
     */
    private String code;

    /**
     * 存储责任链上下文数据的模型
     */
    private ProcessModel processModel;

    /**
     * 责任链中断标识
     */
    private Boolean needBreak;

    /**
     * 流程处理结果
     */
    BasicResultVO response;

}
