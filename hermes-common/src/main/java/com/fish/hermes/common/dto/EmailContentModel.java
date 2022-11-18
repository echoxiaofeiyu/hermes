package com.fish.hermes.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author :  shenzhenxing
 * @Date :  2022/9/28 15:43
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailContentModel extends ContentModel{

    /**
     * 标题
     */
    private String title;

    /**
     * 内容(可写入HTML)
     */
    private String content;

}
