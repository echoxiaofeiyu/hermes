package com.fish.heremes.support.pipeline;

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

}
