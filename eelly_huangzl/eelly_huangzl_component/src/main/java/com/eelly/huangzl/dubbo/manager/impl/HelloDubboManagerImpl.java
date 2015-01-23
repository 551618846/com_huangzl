/** 
* @组件名：eelly_huangzl_component
* @包名：com.eelly.huangzl.dubbo.manager.impl
* @文件名：HelloDubboManagerImpl.java
* @创建时间： 2015年1月5日 上午9:09:46
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.eelly.huangzl.dubbo.manager.impl;

import org.springframework.stereotype.Component;

import com.eelly.huangzl.dubbo.manager.IHelloDubboManager;


/**
 * @类名：HelloDubboManagerImpl
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年1月5日 上午9:09:46
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
@Component
public class HelloDubboManagerImpl implements IHelloDubboManager {

    /**
     * <p>主题：sayHello</p>
     * <p>描述： </p>
     * @param msg
     * @return
     * @see com.eelly.huangzl.dubbo.manager.IHelloDubboManager#sayHello(java.lang.String)
     */
    @Override
    public String sayHello(String msg) {
        String rt = "hello,"+msg;
        System.err.println("+++++++++++++++++++++");
        System.err.println(rt);
        System.err.println("+++++++++++++++++++++");
        return rt;
    }

    @Override
    public void persistMsg(String msgJson, int weight) {
        // TODO Auto-generated method stub
        System.err.println("+++++++++++++++++++++");
        System.err.println(msgJson + "--" +weight);
        System.err.println("+++++++++++++++++++++");
    }

}

