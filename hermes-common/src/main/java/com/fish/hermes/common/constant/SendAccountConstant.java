package com.fish.hermes.common.constant;

/**
 * 发送账号的常量信息汇总
 * <p>
 * (读取nacos的key和前缀)
 * <p>
 * 约定：所有的账号都从10开始，步长为10
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 14:54
 */
public class SendAccountConstant {

    /**
     * 账号约定：所有的账号都从10开始，步长为10
     */
    public static final Integer START = 10;
    public static final Integer STEP = 10;

    /**
     * 邮件 账号
     */
    public static final String EMAIL_ACCOUNT_KEY = "emailAccount";
    public static final String EMAIL_ACCOUNT_PREFIX = "email_";

}
