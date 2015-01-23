/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.core.cache.memcache
* @文件名：MemcacheCache.java
* @创建时间： 2014年11月19日 上午10:37:40
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.eelly.core.cache.memcache;

import org.springframework.cache.Cache;

/**
 * @类名：MemcacheCache
 * @描述: //参考org.springframework.cache.ehcache.EhCacheCache
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年11月19日 上午10:37:40
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class MemcacheCache  implements Cache{

    /**
      * <p>主题：getName</p>
      * <p>描述： </p>
      * @return
      * @see org.springframework.cache.Cache#getName()
      */
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
      * <p>主题：getNativeCache</p>
      * <p>描述： </p>
      * @return
      * @see org.springframework.cache.Cache#getNativeCache()
      */
    @Override
    public Object getNativeCache() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
      * <p>主题：get</p>
      * <p>描述： </p>
      * @param key
      * @return
      * @see org.springframework.cache.Cache#get(java.lang.Object)
      */
    @Override
    public ValueWrapper get(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
      * <p>主题：get</p>
      * <p>描述： </p>
      * @param key
      * @param type
      * @return
      * @see org.springframework.cache.Cache#get(java.lang.Object, java.lang.Class)
      */
    @Override
    public <T> T get(Object key, Class<T> type) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
      * <p>主题：put</p>
      * <p>描述： </p>
      * @param key
      * @param value
      * @see org.springframework.cache.Cache#put(java.lang.Object, java.lang.Object)
      */
    @Override
    public void put(Object key, Object value) {
        // TODO Auto-generated method stub
        
    }

    /**
      * <p>主题：evict</p>
      * <p>描述： </p>
      * @param key
      * @see org.springframework.cache.Cache#evict(java.lang.Object)
      */
    @Override
    public void evict(Object key) {
        // TODO Auto-generated method stub
        
    }

    /**
      * <p>主题：clear</p>
      * <p>描述： </p>
      * @see org.springframework.cache.Cache#clear()
      */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }
    

}

