/** 
 * @组件名：eelly_core
 * @包名：com.eelly.core.util
 * @文件名：SpringContextUtil.java
 * @创建时间： 2014年6月5日 下午5:55:06
 * @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
 */

package com.huangzl.cache.memcache;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @类名：SpringContextUtil
 * @描述: Spring ApplicationContext工具类
 * @创建人：<a href=mailto: huangweiqi@eelly.net>huangweiqi</a>
 * @修改人：
 * @修改时间：2014年6月5日 下午5:55:06
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
@Lazy(value = false)
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /** ApplicationContext实例 */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * @方法名：getaApplicationContext
     * @描述：获取ApplicationContext实例
     * @创建人：<a href=mailto: huangweiqi@eelly.net>huangweiqi</a>
     * @修改人：
     * @修改时间：2014年6月5日 下午5:57:15
     * @return
     * @返回值：ApplicationContext
     * @异常说明：
     */
    public static ApplicationContext getApplicationContext() {
        checkContext();
        return applicationContext;
    }

    /**
     * @方法名：getBean
     * @描述：获取bean
     * @创建人：<a href=mailto: huangweiqi@eelly.net>huangweiqi</a>
     * @修改人：
     * @修改时间：2014年6月5日 下午5:57:53
     * @param name
     *            bean的名称
     * @return bean
     * @返回值：T bean的类型
     * @异常说明：
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * @方法名：getBean
     * @描述：获取bean
     * @创建人：<a href=mailto: huangweiqi@eelly.net>huangweiqi</a>
     * @修改人：
     * @修改时间：2014年6月5日 下午5:58:37
     * @param beanClz
     *            bean的Class类型
     * @return bean
     * @返回值：T bean的类型
     * @异常说明：
     */
    public static <T> T getBean(Class<T> beanClz) {
        checkContext();
        return applicationContext.getBean(beanClz);
    }

    /**
     * @方法名：checkContext
     * @描述：检查Context是否注入
     * @创建人：<a href=mailto: huangweiqi@eelly.net>huangweiqi</a>
     * @修改人：
     * @修改时间：2014年6月5日 下午5:59:16
     * @返回值：void
     * @异常说明：若context并未注入，则抛出IllegalStateException
     */
    private static void checkContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext并未注入");
        }
    }

}
