/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.quartz
* @文件名：CRUDs.java
* @创建时间： 2015年1月13日 上午10:54:47
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.quartz;

import static org.quartz.JobKey.jobKey;
import static org.quartz.impl.matchers.GroupMatcher.jobGroupEquals;
import static org.quartz.impl.matchers.GroupMatcher.triggerGroupEquals;

import java.util.List;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @类名：CRUDs
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年1月13日 上午10:54:47
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class CRUDs {
    
    public static void main(String[] args) throws Exception{

        
        getAllJobs();
        
        getAllTrigger();
        
        getTriggerOfJob();
    }
    
    public static void getAllJobs() throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        // enumerate each job group
        for (String group : sched.getJobGroupNames()) {
            // enumerate each job in group
            for (JobKey jobKey : sched.getJobKeys(jobGroupEquals(group))) {
                System.err.println("Found job identified by: " + jobKey);
            }
        }
    }
    
    public static void getAllTrigger() throws Exception{
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        // enumerate each trigger group
        for (String group : sched.getTriggerGroupNames()) {
            // enumerate each trigger in group
            for (TriggerKey triggerKey : sched.getTriggerKeys(triggerGroupEquals(group))) {
                System.err.println("Found trigger identified by: " + triggerKey);
            }
        }
    }
    
    
    public static void getTriggerOfJob() throws Exception{
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        
        List<Trigger> jobTriggers = (List<Trigger>) sched.getTriggersOfJob(jobKey("job1", "group1"));
        System.err.println(jobTriggers);
    }

}

