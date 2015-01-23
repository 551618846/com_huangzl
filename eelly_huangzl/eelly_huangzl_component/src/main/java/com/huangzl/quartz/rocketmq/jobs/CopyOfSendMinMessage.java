/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.quartz.rocketmq.jobs
* @文件名：SendMinMessage.java
* @创建时间： 2015年1月13日 上午10:39:36
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.quartz.rocketmq.jobs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.springframework.context.ApplicationContext;

import com.eelly.common.dubbo.manager.IRocketMQFailHandlerDubboManager;
import com.eelly.core.constant.RocketMQConstant;

/**
 * @类名：SendMinMessage
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年1月13日 上午10:39:36
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
@DisallowConcurrentExecution//保证一次只有一个实例运行,否则数据库需要加锁
public class CopyOfSendMinMessage implements Job{

    private static final Logger logger = LogManager.getLogger(CopyOfSendMinMessage.class);
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        if(logger.isInfoEnabled()){
            logger.info("execute job {} at thread {}", this.getClass(), Thread.currentThread().getName());
        }
        
        try {
            
            SchedulerContext qctx = context.getScheduler().getContext();
            ApplicationContext ctx = (ApplicationContext)qctx.get("applicationContext");
            
            //重发消息.dubbo服务
            IRocketMQFailHandlerDubboManager rocketMQFailHandlerDubboManager = ctx.getBean(IRocketMQFailHandlerDubboManager.class);
            
            //避免dubbo超时,导致多个实例在跑;需要等待dubbo返回,本方法设置超时为10分钟
            rocketMQFailHandlerDubboManager.sendMsgsByWeight(RocketMQConstant.WEIGHT_MAX);
            
        } catch (Exception e) {
            logger.catching(e);
        }
        
        if(logger.isInfoEnabled()){
            logger.info("execute job end {} at thread {}", this.getClass(), Thread.currentThread().getName());
        }
    }

}

