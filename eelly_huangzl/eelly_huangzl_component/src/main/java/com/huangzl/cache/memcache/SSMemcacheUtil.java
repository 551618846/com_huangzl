/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.core.cache.memcache
* @文件名：MemcacheUtil.java
* @创建时间： 2014年11月20日 上午9:04:44
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.cache.memcache;

import java.util.Collection;

import org.springframework.context.ApplicationContext;

import com.eelly.core.util.SpringContextUtil;
import com.google.code.ssm.Cache;

/**
 * @类名：MemcacheUtil
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年11月20日 上午9:04:44
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class SSMemcacheUtil {
    
    public static Cache getCache(String name){
        ApplicationContext context = SpringContextUtil.getApplicationContext();
        Collection<Cache> list = context.getBeansOfType(Cache.class).values();
        for(Cache c : list){
            String cname = c.getName();
            if(cname.equals(name)){
                return c;
            }
        }
        return null;
    }

}

