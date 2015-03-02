/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.rocketmq
* @文件名：CreateProducer.java
* @创建时间： 2015年1月27日 下午3:11:58
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.rocketmq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

/**
 * @类名：CreateProducer
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年1月27日 下午3:11:58
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class CreateProducer {

    /**
     * @方法名：main
     * @描述：TODO 
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2015年1月27日 下午3:11:58
     * @param args 
     * @throws Exception 
     * @返回值：void 
     * @异常说明：
     */
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("groupName");
//        String topic = "huangzl";
        producer.setNamesrvAddr("172.18.107.197:9876");
        
        producer.start();

    }

}

