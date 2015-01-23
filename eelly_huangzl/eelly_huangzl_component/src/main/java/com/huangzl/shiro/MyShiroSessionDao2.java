/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.mvc.common
* @文件名：MyShiroSessionDao.java
* @创建时间： 2014年11月26日 下午7:00:38
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.shiro;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

/**
 * @类名：MyShiroSessionDao
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年11月26日 下午7:00:38
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class MyShiroSessionDao2 extends AbstractSessionDAO implements CacheManagerAware{
    
    private CacheManager cacheManager;
    
    private SessionIdGenerator sessionIdGenerator;
    
    public SessionIdGenerator getSessionIdGenerator() {
        return sessionIdGenerator;
    }
    
    public void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator) {
        this.sessionIdGenerator = sessionIdGenerator;
    }
    
    private Cache<Serializable, Session> getSessionCache(){
        Cache<Serializable, Session> cache = null;
        CacheManager mgr = getCacheManager();
        if (mgr != null) {
            String name = "shiro-activeSessionCache";
            cache = mgr.getCache(name);
        }
        return cache;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        getSessionCache().put(session.getId(), session);
        
    }

    @Override
    public void delete(Session session) {
        getSessionCache().remove(session.getId());
        
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return getSessionCache().values();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = sessionIdGenerator.generateId(session);
        this.assignSessionId(session, sessionId);
        getSessionCache().put(sessionId, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return getSessionCache().get(sessionId);
    }

    /**
      * <p>主题：setCacheManager</p>
      * <p>描述： </p>
      * @param cacheManager
      * @see org.apache.shiro.cache.CacheManagerAware#setCacheManager(org.apache.shiro.cache.CacheManager)
      */
    @Override
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        
    }
    public CacheManager getCacheManager() {
        return this.cacheManager;
    }
    
    

}

