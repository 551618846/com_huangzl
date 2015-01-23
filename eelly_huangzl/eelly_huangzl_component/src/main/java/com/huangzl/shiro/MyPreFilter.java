/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.mvc.common
* @文件名：MyPreFilter.java
* @创建时间： 2014年11月26日 上午8:38:47
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.shiro;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.client.session.SingleSignOutHandler;

/**
 * @类名：MyPreFilter
 * @描述: web集群环境下shiro每个节点生成session缓存的问题,有2个方法
 * 1,修改authc拦截器,不创建shirosession,先跳转到CAS登录,登录成功后再根据用户名获取/生成session[跨域可以][不用考虑cookie]
 * 2,仍使用默认authc拦截器,创建session后[已写cookie]再跳转到CAS;登录成功后返回时可以拿到shiro-session[因为有cookie.],然后把sessionID写到主域名cookie.那么同主域[跨域不行]的应用根据cookie可以公用一个session[这个可以不用:自定义拦截器,从主域名读取cookie,读取到则保存到线程,后面创建session时id从线程拿]
 * 3,结合1-2,修改authc拦截器,不创建shirosession;跳转到CAS登录;登录成功后使用userID注入线程,后面使用时从线程拿(ID生成器或者拿cookie方法,从线程拿)
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年11月26日 上午8:38:47
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class MyPreFilter implements Filter{
    
    private static final SingleSignOutHandler handler = new SingleSignOutHandler();

    
    
//    private ShiroSessionIdGenerator generator = new ShiroSessionIdGenerator();
    private ShiroSimpleCookie generator = new ShiroSimpleCookie();
    
    private String cookieName = "JSESSIONID";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        /*//局域网内都是一个IP,但可能是两个用户!
        String host = request.getRemoteHost();
        String shiroSessionId = host;*/
        
        
        
        
        final HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        
        if (handler.isTokenRequest(httpRequest)) {//给ID生成器注入用户名
//            handler.recordSession(request);
        }
        
        String cookieValue = readValue(httpRequest, httpResponse);
        if(!StringUtils.isBlank(cookieValue)){//获取到cookie(主域名+子域名),说明已经登录,[那么可以存储到线程,后面从cookie拿时可以从线程拿,待测试//TODO][或者:那么初始化shiro sessionId,后面本应用创建的session是可以从缓存拿到,还是覆盖缓存?待测试//TODO]
            generator.setId(cookieValue);
            
            //cookie何时写到主域名?登录成功后,即CAS返回时,需要重写一下CasFilter
        }
        
        chain.doFilter(request, response);
        
    }

    
    @Override
    public void destroy() {
    }
    
    
    private String readValue(HttpServletRequest request, HttpServletResponse ignored) {
        String name = cookieName;
        String value = null;
        javax.servlet.http.Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            value = cookie.getValue();
        }

        return value;
    }
    
    private static javax.servlet.http.Cookie getCookie(HttpServletRequest request, String cookieName) {
        javax.servlet.http.Cookie cookies[] = request.getCookies();//TODO 是否能拿到主域名的cookie?
        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    //********************************//
                    String vl = cookie.getValue();
                    if(!StringUtils.isBlank(vl) && vl.startsWith(ShiroSessionIdGenerator.ID_PREFIX)){
                        return cookie;
                    }
                    //********************************//
                }
            }
        }
        return null;
    }
    

}

