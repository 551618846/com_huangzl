/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.quartz
* @文件名：HelloJob.java
* @创建时间： 2015年1月12日 上午11:32:17
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


@DisallowConcurrentExecution//防止并发
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        
        System.err.println("hello,world" );
        
    }

}

