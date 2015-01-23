/** 
* @组件名：eelly_huangzl_component
* @包名：com.eelly.core.rocketmq
* @文件名：SendMsgFailHandlerTest.java
* @创建时间： 2015年1月21日 下午3:48:00
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.eelly.core.rocketmq;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.rocketmq.common.message.Message;
import com.eelly.core.constant.RocketMQConstant;
import com.eelly.core.test.BaseTestCase;


/**
 * @类名：SendMsgFailHandlerTest
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年1月21日 下午3:48:00
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class SendMsgFailHandlerTest extends BaseTestCase{
    
    @Resource
    private SendMsgFailHandler handler;

    /**
     * Test method for {@link com.eelly.core.rocketmq.SendMsgFailHandler#handle(java.lang.String, com.alibaba.rocketmq.common.message.Message)}.
     * @throws Exception 
     */
    @Test
    public void testHandle() throws Exception {
        String groupName = "testGroup";
        String topic = "testTopic";
        String tags = "testTtags";
        String keys = "testTkeys";
        String body = "testTbody";
        
        Message msg = new Message(topic, tags, keys, body.getBytes());
        msg.putUserProperty(RocketMQConstant.MESSAGE_PROPERTY_FAIL_WEIGHT,RocketMQConstant.WEIGHT_NORM);
        
        handler.handle(groupName, msg);
        
        Thread.sleep(1000 * 60 * 10);
        
    }

}

