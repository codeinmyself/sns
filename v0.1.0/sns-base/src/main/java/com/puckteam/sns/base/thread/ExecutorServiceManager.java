package com.puckteam.sns.base.thread;

import java.util.concurrent.*;

/**
 * Created by PoemWhite on 16/4/25.
 */
public class ExecutorServiceManager {
    private int corePoolSize=30;
    private int maximumPoolSize=-1;
    private long keepAliveTime=100;
    private int workQueueSize=0;
    private TimeUnit timeUnit=TimeUnit.MILLISECONDS;
    private BlockingQueue<Runnable> workQueue=null;
    private RejectedExecutionHandler handler=new ThreadPoolExecutor.CallerRunsPolicy();

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getWorkQueueSize() {
        return workQueueSize;
    }

    public void setWorkQueueSize(int workQueueSize) {
        this.workQueueSize = workQueueSize;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public BlockingQueue<Runnable> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
    }

    public RejectedExecutionHandler getHandler() {
        return handler;
    }

    public void setHandler(RejectedExecutionHandler handler) {
        this.handler = handler;
    }

    public ExecutorService create(){
        ExecutorService executorService = null;
        if(maximumPoolSize<=0){
            maximumPoolSize=Integer.MAX_VALUE;
        }
        if(workQueue==null){
            if(workQueueSize<=0){
                workQueue=new LinkedBlockingQueue<Runnable>();
                workQueueSize=Integer.MAX_VALUE;
            }else{
                workQueue=new LinkedBlockingQueue<Runnable>(workQueueSize);
            }
        }
        executorService = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                timeUnit,
                workQueue,
                handler);
        return executorService;
    }
}
