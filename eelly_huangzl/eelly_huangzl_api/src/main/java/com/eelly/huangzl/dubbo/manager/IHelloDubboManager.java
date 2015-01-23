/** 
* @组件名：eelly_huangzl_api
* @包名：com.eelly.huangzl.dubbo.manager
* @文件名：IHelloDubboManager.java
* @创建时间： 2015年1月5日 上午9:04:04
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.eelly.huangzl.dubbo.manager;

/**
 * @类名：IHelloDubboManager
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年1月5日 上午9:04:04
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public interface IHelloDubboManager {
    
    public String sayHello(String msg);
    
    public void persistMsg(String msgJson, int weight);

}

