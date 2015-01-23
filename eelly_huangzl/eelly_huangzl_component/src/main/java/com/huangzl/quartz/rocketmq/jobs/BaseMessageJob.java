/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.quartz.rocketmq.jobs
* @文件名：BaseMessageJob.java
* @创建时间： 2015年1月21日 上午10:16:34
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.quartz.rocketmq.jobs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.springframework.context.ApplicationContext;

import com.eelly.common.dubbo.manager.IRocketMQFailHandlerDubboManager;

/**
 * @类名：BaseMessageJob
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年1月21日 上午10:16:34
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public abstract class BaseMessageJob implements Job{

    private static final Logger logger = LogManager.getLogger(BaseMessageJob.class);
    
    /**
     * @方法名：sendMessageByWeight
     * @描述：抽象方法,实现类使用dubbo服务发送rocketMQ失败后持久化的消息 
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2015年1月21日 上午10:31:12
     * @param rocketMQFailHandlerDubboManager 
     * @返回值：void 
     * @异常说明：
     */
    abstract public void sendMessageByWeight(IRocketMQFailHandlerDubboManager rocketMQFailHandlerDubboManager);
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        if(logger.isInfoEnabled()){
            logger.info("execute message job {} ", context.getJobDetail().getJobClass());
        }
        
        try {
            
            //Thread.sleep(1000 * 10);
            
            SchedulerContext qctx = context.getScheduler().getContext();
            ApplicationContext ctx = (ApplicationContext)qctx.get("applicationContext");
            
            
            //重发消息.dubbo服务
            IRocketMQFailHandlerDubboManager rocketMQFailHandlerDubboManager = ctx.getBean(IRocketMQFailHandlerDubboManager.class);
            
            sendMessageByWeight(rocketMQFailHandlerDubboManager);
            
            
        } catch (Exception e) {
            logger.catching(e);
        }
        
        if(logger.isInfoEnabled()){
            logger.info("execute message job end {} ", context.getJobDetail().getJobClass());
        }
    }

}

