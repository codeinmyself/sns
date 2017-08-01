package com.puckteam.sns.core.support.mail.sender;

import com.puckteam.sns.base.thread.ExecutorServiceManager;
import com.puckteam.sns.core.support.mail.strategy.EmailActivateStrategy;
import com.puckteam.sns.core.support.mail.strategy.base.MailStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by PoemWhite on 2017/1/19.
 */
public class MailSendHandlerManager {

    static Logger logger = LogManager.getLogger();

    private ExecutorService executorService;

    @PostConstruct
    public void init(){

        ExecutorServiceManager esm = new ExecutorServiceManager();
        int cpuNum = Runtime.getRuntime().availableProcessors();
        if(cpuNum<=0){
            cpuNum = 10;
        }
        int corePoolSize = (cpuNum+1)*2;//处理线程数
        int maximumPoolSize = corePoolSize*2;//线程池最大线程数
        int keepAliveTime = 60;//空闲线程最大存活时间
        int workQueueSize = Integer.MAX_VALUE;//等待队列长度

        esm.setCorePoolSize(corePoolSize);
        esm.setMaximumPoolSize(maximumPoolSize);
        esm.setKeepAliveTime(keepAliveTime);
        esm.setTimeUnit(TimeUnit.SECONDS);
        esm.setWorkQueueSize(workQueueSize);

        executorService = esm.create();

        if(logger.isDebugEnabled()){
            logger.debug("executorService init");
        }
    }

    @PreDestroy
    public void destroy(){
        if(logger.isDebugEnabled()){
            logger.debug("executorService destroy");
        }
        ExecutorServiceManager.shutdownAndAwaitTermination(executorService, 300);
    }

    public void onMailSend(final MailStrategy mailStrategy) {

        try {

            executorService.execute(new Runnable() {

                public void run() {

                    try{
                        //发送邮件
                        mailStrategy.sendMail();

                    }catch (Throwable t) {
                        logger.error(t);
                    }

                }
            });

        } catch (Throwable tt) {
            logger.error(tt);
        }

    }

    public static void main(String []args){

        final MailSendHandlerManager mailSendHandlerManager = new MailSendHandlerManager();

        mailSendHandlerManager.init();

        MailStrategy mailStrategy = new EmailActivateStrategy("1111111111111222222222","531495203@qq.com");
        mailSendHandlerManager.onMailSend(mailStrategy);


    }
}
