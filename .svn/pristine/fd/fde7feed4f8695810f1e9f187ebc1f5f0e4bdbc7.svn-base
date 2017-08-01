package com.puckteam.sns.core.support.mail.strategy;

import com.puckteam.sns.core.support.mail.strategy.base.MailStrategy;

/**
 * Created by PoemWhite on 2017/1/19.
 */
public class EmailActivateStrategy extends MailStrategy {

    private String link;

    public EmailActivateStrategy(String token,String receiver){

        super();

        link = "http://192.168.1.106/user/activate.html?token="+token;
        this.receiver = receiver;
        this.mailManager.setSubject("[臻元艺术品交易网] Email 地址验证");
    }

    public int sendMail(){

        message = "Email 地址验证\n\n"
                + "这封信是由 臻元艺术品交易网 发送的。"
                + "\n\n\n"
                + "您收到这封邮件，是由于在 臻元艺术品交易网 进行了新用户注册，或用户修改 Email 使用了这个邮箱地址。"
                + "\n"
                + "如果您并没有访问过 臻元艺术品交易网，或没有进行上述操作，请忽略这封邮件。您不需要退订或进行其他进一步的操作。"
                + "\n\n\n"
                + "----------------------------------------------------------------------"
                + "\n"
                + "帐号激活说明"
                + "\n"
                + "----------------------------------------------------------------------"
                + "\n\n\n"
                + "如果您是 臻元艺术品交易网 的新用户，或在修改您的注册 Email 时使用了本地址，我们需要对您的地址有效性进行验证以避免垃圾邮件或地址被滥用。"
                + "\n\n"
                + "您只需点击下面的链接即可激活您的帐号："
                + "\n"
                + link
                + "\n"
                + "(如果上面不是链接形式，请将该地址手工粘贴到浏览器地址栏再访问)"
                + "\n\n"
                + "感谢您的访问，祝您使用愉快！"
                + "\n\n"
                + "此致"
                + "\n"
                + "臻元艺术品交易网 管理团队."
                + "\n"
                + "http://192.168.1.106/";

        return super.sendMail();
    }
}
