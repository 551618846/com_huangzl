/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.mvc.common
* @文件名：ShiroSimpleCookie.java
* @创建时间： 2014年11月24日 上午11:47:24
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.shiro;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @类名：ShiroSimpleCookie
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年11月24日 上午11:47:24
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class ShiroSimpleCookie extends SimpleCookie{
    private static ThreadLocal<Serializable> threadShiroSessionId = new ThreadLocal<Serializable>();
    
    public static void setId(String id){
        threadShiroSessionId.set(id);
    }
    
    
    public ShiroSimpleCookie() {
        super();
    }

    public ShiroSimpleCookie(String name) {
        super(name);
        setHttpOnly(true);
    }

    public ShiroSimpleCookie(org.apache.shiro.web.servlet.Cookie arg0) {
        super(arg0);
    }
    
    
    private static final transient Logger log = LoggerFactory.getLogger(ShiroSimpleCookie.class);
    
    public String readValue(HttpServletRequest request, HttpServletResponse ignored) {
        String name = getName();
        String value = null;
        javax.servlet.http.Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            value = cookie.getValue();
            log.debug("Found '{}' cookie value [{}]", name, value);
        } else {
            log.trace("No '{}' cookie value", name);
        }
        
        /*************************************/
        if(value == null){
            value = String.valueOf(threadShiroSessionId.get());//在doGetAuthenticationInfo中设置
        }
        /*************************************/

        return value;
    }
    
    private static javax.servlet.http.Cookie getCookie(HttpServletRequest request, String cookieName) {
        javax.servlet.http.Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    //********************************//
                    String vl = cookie.getValue();
                    if(!StringUtils.isBlank(vl) && vl.startsWith("el_")){
                        return cookie;
                    }
                    //********************************//
                }
            }
        }
        return null;
    }

}

