/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.mvc.common
* @文件名：ShiroSessionIdGenerator.java
* @创建时间： 2014年11月24日 上午10:28:10
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.shiro;

import java.io.Serializable;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @类名：ShiroSessionIdGenerator
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年11月24日 上午10:28:10
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class ShiroSessionIdGenerator  implements SessionIdGenerator{
    
    public static final String ID_PREFIX = "el_";
    
    private static ThreadLocal<Serializable> threadShiroSessionId = new ThreadLocal<Serializable>();//必须全局
    
    public static void setId(String id){
        threadShiroSessionId.set(id);
    }

    @Override
    public Serializable generateId(Session session) {
        Serializable tId = threadShiroSessionId.get();
        if(tId == null){
            tId = ID_PREFIX + UUID.randomUUID().toString();
            threadShiroSessionId.set(tId);
        }
        return tId;
        
        
        /*String id = null;
        
        try {
//            WebUtils.getRequest(key);
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession httpSession = request.getSession(false);
            
            id = httpSession.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "el_"+UUID.randomUUID().toString();*/
        
    }

}

