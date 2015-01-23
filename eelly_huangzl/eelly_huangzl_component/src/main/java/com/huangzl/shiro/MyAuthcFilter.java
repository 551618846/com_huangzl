/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.mvc.common
* @文件名：MyPreFilter.java
* @创建时间： 2014年11月26日 上午8:38:47
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.SavedRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @类名：MyPreFilter
 * @描述: 重写默认的authc拦截器,主要是解决跳转到CAS登录前会生成shiro-session(因为要保存原请求地址),导致每个应用有有自己的shiro-session
 * 解决办法是重写saveRequestAndRedirectToLogin方法,原请求地址直接保存到HTTPsession.CAS登录成功后再创建shiro-session
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年11月26日 上午8:38:47
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class MyAuthcFilter extends FormAuthenticationFilter{
    private static final Logger log = LoggerFactory.getLogger(MyAuthcFilter.class);
    
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }

            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }
    
    //重写方法,session不存在不创建Shiro session;我们直接跳转到CAS登录;登录后
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
//        saveRequest(request);//这里保存原请求会创建Shiro session;我们直接保存到HTTPsession,跳转到CAS登录;登录回来再根据用户名创建session,见MyShiro(重写CasRealm的doGetAuthenticationInfo)
        /**************************************************/
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        if(session != null){
            saveRequest(request);
        }else{
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            SavedRequest savedRequest = new SavedRequest(httpRequest);
            String fallbackUrl = savedRequest.getRequestUrl();
            

            ShiroHttpServletRequest t = (ShiroHttpServletRequest)request;
            httpRequest = (HttpServletRequest)t.getRequest();
            HttpSession httpSession = httpRequest.getSession(true);
            httpSession.setAttribute("el_fallbackUrl", fallbackUrl);
        }
        /**************************************************/
        
        redirectToLogin(request, response);
    }

}

