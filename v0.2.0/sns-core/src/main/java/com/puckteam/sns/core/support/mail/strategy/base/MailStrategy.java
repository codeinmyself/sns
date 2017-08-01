package com.puckteam.sns.core.support.mail.strategy.base;

/**
 * Created by PoemWhite on 2017/1/19.
 */
public class MailStrategy {

    protected String message;
    protected String receiver;
    protected MailManager mailManager;

    public MailStrategy(){
        mailManager = new MailManager();
    }

    public int sendMail(){

        mailManager.setEmailMsg(message);
        int result = mailManager.sendMail(receiver);

        if(0!=result){

        }

        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public MailManager getMailManager() {
        return mailManager;
    }

    public void setMailManager(MailManager mailManager) {
        this.mailManager = mailManager;
    }
}
