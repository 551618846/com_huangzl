/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.mvc.common
* @文件名：MyShiroSessionDao.java
* @创建时间： 2014年11月26日 下午7:00:38
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.shiro;

import java.io.Serializable;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

/**
 * @类名：MyShiroSessionDao
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年11月26日 下午7:00:38
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class MyShiroSessionDao extends EnterpriseCacheSessionDAO{
    
    public Serializable create(Session session) {
        Serializable sessionId = super.create(session);
//        Serializable sessionId = create(session);
        /***************************************************/
        Session x = getActiveSessionsCache().get(sessionId);
        if(x == null){
            cache(session, sessionId);
        }
        /***************************************************/
        return sessionId;
    }
    
    private Cache<Serializable, Session> getActiveSessionsCacheLazy() {
        if (super.getActiveSessionsCache() == null) {
            Cache<Serializable, Session> cache = super.createActiveSessionsCache();
            super.setActiveSessionsCache(cache);
        }
        return super.getActiveSessionsCache();
    }
    
    protected void cache(Session session, Serializable sessionId) {
        if (session == null || sessionId == null) {
            return;
        }
        Cache<Serializable, Session> cache = getActiveSessionsCacheLazy();
        if (cache == null) {
            return;
        }
        cache(session, sessionId, cache);
    }
    
    protected void cache(Session session, Serializable sessionId, Cache<Serializable, Session> cache) {
        cache.put(sessionId, session);
    }

}

