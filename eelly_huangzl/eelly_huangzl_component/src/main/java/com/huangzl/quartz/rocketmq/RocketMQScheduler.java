/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.quartz
* @文件名：RocketMQScheduler.java
* @创建时间： 2015年1月13日 上午8:47:43
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.quartz.rocketmq;


import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.huangzl.quartz.rocketmq.jobs.SendMaxMessage;
import com.huangzl.quartz.rocketmq.jobs.SendMinMessage;
import com.huangzl.quartz.rocketmq.jobs.SendNormMessage;
import com.huangzl.quartz.rocketmq.jobs.SendXMaxMessage;
import com.huangzl.quartz.rocketmq.jobs.SendXMinMessage;

/**
 * @类名：RocketMQScheduler
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年1月13日 上午8:47:43
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class RocketMQScheduler {
    
    
    private static SchedulerFactory sf = new StdSchedulerFactory();
    private static Scheduler sched;


    static {
        try {
            sched = sf.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    
    private static String JobGroup = "jobgroup_rocketmq";
    private static String triggerGroup = "triggergroup_rocketmq";
    

    
    public static void main(String[] args) throws Exception {
        
        
        
        scheduleJobs_max();
        scheduleJobs_min();
        scheduleJobs_norm();
        scheduleJobs_xmax();
        scheduleJobs_xmin();
        
        
//        startScheduler();
        
    }
    
    
    public static void scheduleJobs_xmin() throws Exception{
        String jobName = "job_rmq_send_xmin_msg";
        JobDetail job = JobBuilder.newJob(SendXMinMessage.class).withIdentity(jobName, JobGroup)
                .build();

        // Define a new Trigger
        String triggerName = "trigger_rmq_send_xmin_msg";
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(4)
                                .withMisfireHandlingInstructionNextWithExistingCount()
                                .repeatForever()).build();

        sched.scheduleJob(job, trigger);
        
    }
    
    public static void scheduleJobs_min() throws Exception{
        String jobName = "job_rmq_send_min_msg";
        JobDetail job = JobBuilder.newJob(SendMinMessage.class).withIdentity(jobName, JobGroup)
                .build();
        
        // Define a new Trigger
        String triggerName = "trigger_rmq_send_min_msg";
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(4)
                        .withMisfireHandlingInstructionNextWithExistingCount()
                        .repeatForever()).build();
        
        sched.scheduleJob(job, trigger);
        
    }
    
    public static void scheduleJobs_norm() throws Exception{
        String jobName = "job_rmq_send_norm_msg";
        JobDetail job = JobBuilder.newJob(SendNormMessage.class).withIdentity(jobName, JobGroup)
                .build();
        
        // Define a new Trigger
        String triggerName = "trigger_rmq_send_norm_msg";
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(4)
                        .withMisfireHandlingInstructionNextWithExistingCount()
                        .repeatForever()).build();
        
        sched.scheduleJob(job, trigger);
        
    }
    
    public static void scheduleJobs_max() throws Exception{
        String jobName = "job_rmq_send_max_msg";
        JobDetail job = JobBuilder.newJob(SendMaxMessage.class).withIdentity(jobName, JobGroup)
                .build();
        
        // Define a new Trigger
        String triggerName = "trigger_rmq_send_max_msg";
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(4)
                        .withMisfireHandlingInstructionNextWithExistingCount()
                        .repeatForever()).build();
        
        sched.scheduleJob(job, trigger);
        
    }
    
    
    public static void scheduleJobs_xmax() throws Exception{
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        
        String jobName = "job_rmq_send_xmax_msg";
        
        
        String triggerName = "trigger_rmq_send_xmax_msg";
        
        
        

        // define the job and tie it to our HelloJob class
        JobDetail job = JobBuilder.newJob(SendXMaxMessage.class).withIdentity(jobName, JobGroup).build();
        
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroup).startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(4)// 如果job执行时间大于间隔,按照Misfire处理??
                        .withMisfireHandlingInstructionNextWithExistingCount().repeatForever())//Misfire执行下一次
                .build();
        
        sched.scheduleJob(job, trigger);

//        sched.start();
    }
    
    
    public static void startScheduler() throws Exception{
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        
        sched.start();
    }

}

