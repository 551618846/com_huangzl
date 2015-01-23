/** 
 * @组件名：eelly_huangzl_component
 * @包名：com.huangzl.quartz
 * @文件名：Test.java
 * @创建时间： 2015年1月12日 上午10:42:25
 * @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
 */

package com.huangzl.quartz;

import static org.quartz.JobBuilder.newJob;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Test {

    public static void main(String[] args) throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();// 利用java5 import static导入JobBuilder

        // Trigger the job to run on the next round minute
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)// 
                        .withMisfireHandlingInstructionNextWithExistingCount().repeatForever())//
                .build();


        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);

        sched.start();

        Thread.sleep(90L * 1000L);

        sched.shutdown(true);
    }

}
