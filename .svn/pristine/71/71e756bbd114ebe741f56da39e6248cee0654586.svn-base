package com.puckteam.sns.core.support.mail.strategy.base;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;

/**
 * Created by PoemWhite on 2017/1/19.
 */
public class MailManager {

    static Logger logger = LogManager.getLogger();

    protected String smtpHost = null;
    // 发送者用户名
    protected String user = null;
    // 密码
    protected String passwd = null;
    // 发送邮箱
    protected String sender = null;
    protected String senderName = null;

    protected String receiver = null;
    protected String receiveName = "";
    protected String subject = null;


    protected SimpleEmail email = null;

    protected String emailMsg = "";

    public MailManager() {
        email = new SimpleEmail();
        setSmtpHost("smtp.126.com");
        setUser("puckart@126.com");
        setSender("puckart@126.com");
        setSenderName("puckart");
        setPasswd("puckteam123");
    }


    public int sendMail() {
        try {
            email.setHostName(smtpHost);
            // 登陆使用的账号密码
            email.setAuthentication(user, passwd);
            email.addTo(receiver, receiveName);
            // 发送者邮箱及发送者姓名
            email.setFrom(sender, senderName);
            // 主题
            email.setSubject(subject);

            // 发送内容
            email.setMsg(emailMsg);
            // 发送
            email.send();

        } catch (EmailException e) {
            logger.error(e);
        }

        return 0;
    }

    public int sendMail(String receiver) {
        try {
            email.setHostName(smtpHost);
            // 登陆使用的账号密码
            email.setAuthentication(user, passwd);
            email.addTo(receiver, "");
            // 发送者邮箱及发送者姓名
            email.setFrom(sender, senderName);
            // 主题
            email.setSubject(subject);

            // 发送内容
            email.setMsg(emailMsg);
            // 发送
            email.send();
        } catch (EmailException e) {
            logger.error(e);
        }
        return 0;
    }

    public int sendMail(List<String> receivers) {
        try {
            email.setHostName(smtpHost);
            // 登陆使用的账号密码
            email.setAuthentication(user, passwd);
            // 发送者邮箱及发送者姓名
            email.setFrom(sender, senderName);
            // 主题
            email.setSubject(subject);

            Iterator<String> it = receivers.iterator();
            while (it.hasNext()) {
                receiver = it.next();
                email.addTo(receiver, "");
                // 发送内容
                email.setMsg(emailMsg);
                // 发送
                email.send();
            }

        } catch (EmailException e) {
            logger.error(e);
        }
        return 0;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public SimpleEmail getEmail() {
        return email;
    }

    public void setEmail(SimpleEmail email) {
        this.email = email;
    }

    public String getEmailMsg() {
        return emailMsg;
    }

    public void setEmailMsg(String emailMsg) {
        this.emailMsg = emailMsg;
    }
}
