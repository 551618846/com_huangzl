/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.core.cache
* @文件名：MemcacheCacheManager.java
* @创建时间： 2014年11月19日 上午10:24:20
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.cache.memcache;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * @类名：MemcacheCacheManager
 * @描述: //参考org.springframework.cache.ehcache.EhCacheCacheManager//不实现事务
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年11月19日 上午10:24:20
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class MemcacheCacheManager implements CacheManager{

    /**
      * <p>主题：getCache</p>
      * <p>描述： </p>
      * @param name
      * @return
      * @see org.springframework.cache.CacheManager#getCache(java.lang.String)
      */
    @Override
    public Cache getCache(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
      * <p>主题：getCacheNames</p>
      * <p>描述： </p>
      * @return
      * @see org.springframework.cache.CacheManager#getCacheNames()
      */
    @Override
    public Collection<String> getCacheNames() {
        // TODO Auto-generated method stub
        return null;
    }
    
}

