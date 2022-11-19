package com.fish.hermes.handler.handler.impl;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.fish.hermes.common.constant.SendAccountConstant;
import com.fish.hermes.common.domain.TaskInfo;
import com.fish.hermes.common.dto.EmailContentModel;
import com.fish.hermes.common.enums.ChannelType;
import com.fish.hermes.handler.handler.BaseHandler;
import com.fish.hermes.handler.handler.Handler;
import com.fish.hermes.support.entity.MessageTemplate;
import com.fish.hermes.support.utils.AccountUtils;
import com.google.common.base.Throwables;
import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.GeneralSecurityException;

/**
 * @Description: 邮件发送处理
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 14:46
 */
@Component
@Slf4j
public class EmailHandler extends BaseHandler implements Handler {

    @Resource
    private AccountUtils accountUtils;


    public EmailHandler() {
        channelCode = ChannelType.EMAIL.getCode();
        //TODO 限流
    }

    @Override
    public boolean handler(TaskInfo taskInfo) {
        EmailContentModel emailContentModel = (EmailContentModel) taskInfo.getContentModel();
        MailAccount account = getAccountConfig(taskInfo.getSendAccount());
        try {
            MailUtil.send(account, taskInfo.getReceiver(), emailContentModel.getTitle(), emailContentModel.getContent(),
                    true, null);
        } catch (Exception e) {
            log.error("EmailHandler#handler fail!{},params:{}", Throwables.getStackTraceAsString(e), taskInfo);
            return false;
        }
        return true;
    }


    @Override
    public void recall(MessageTemplate messageTemplate) {

    }

    private MailAccount getAccountConfig(Integer sendAccount) {
        MailAccount account = accountUtils.getAccount(sendAccount, SendAccountConstant.EMAIL_ACCOUNT_KEY,
                SendAccountConstant.EMAIL_ACCOUNT_PREFIX, MailAccount.class);

        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            account.setAuth(account.isAuth()).setStarttlsEnable(account.isStarttlsEnable()).setSslEnable(account.isSslEnable()).setCustomProperty("mail.smtp.ssl.socketFactory", sf);
            account.setTimeout(25000).setConnectionTimeout(25000);
        } catch (GeneralSecurityException e) {
            log.error("EmailHandler#getAccount fail!{}", Throwables.getStackTraceAsString(e));
        }
        return account;
    }
}
