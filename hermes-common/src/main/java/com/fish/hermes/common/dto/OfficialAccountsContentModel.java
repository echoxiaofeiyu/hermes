package com.fish.hermes.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Description:
 * @Author :  shenzhenxing
 * @Date :  2022/9/28 15:43
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficialAccountsContentModel  extends ContentModel {
    /**
     * 模板消息发送的数据
     */
    Map<String, String> map;

    /**
     * 模板消息跳转的url
     */
    String url;
}
