package com.fish.hermes.common.dto;

/**
 * @Description: 短信内容模型
 * @Author :  shenzhenxing
 * @Date :  2022/9/28 15:44
 */
public class SmsContentModel extends ContentModel {

    /**
     * 短信发送内容
     */
    private String content;

    /**
     * 短信发送链接
     */
    private String url;
}
