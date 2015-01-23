/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.quartz.rocketmq.jobs
* @文件名：SendXMaxMessage.java
* @创建时间： 2015年1月13日 上午10:38:28
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.quartz.rocketmq.jobs;

import org.quartz.DisallowConcurrentExecution;

import com.eelly.common.dubbo.manager.IRocketMQFailHandlerDubboManager;
import com.eelly.core.constant.RocketMQConstant;

/**
 * @类名：SendXMaxMessage
 * @描述: queartz任务,发送权重为RocketMQConstant.WEIGHT_XMAX的消息
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年1月13日 上午10:38:28
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
@DisallowConcurrentExecution//保证一次只有一个实例运行,否则数据库需要加锁
public class SendXMaxMessage extends BaseMessageJob{
    
    @Override
    public void sendMessageByWeight(IRocketMQFailHandlerDubboManager rocketMQFailHandlerDubboManager) {
        //避免dubbo超时,导致多个实例在跑;需要等待dubbo返回,本方法设置超时为10分钟
        rocketMQFailHandlerDubboManager.sendMsgsByWeight(RocketMQConstant.WEIGHT_XMAX);
    }
}

