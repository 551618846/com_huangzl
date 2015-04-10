/** 
 * @组件名：eelly_core
 * @包名：com.eelly.core.util.annotation
 * @文件名：EellyAnnotation4Service.java
 * @创建时间： 2014年7月24日 上午9:04:44
 * @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
 */

package com.huangzl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @类名：EellyAnnotation4Method
 * @描述: 定义方法的注解
 * @创建人：<a href=mailto: wujinhua@eelly.net>吴进华</a>
 * @修改人：
 * @修改时间：2014年7月24日 上午9:04:44
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EellyAnnotation4Method {

    /**
     * @方法名：value
     * @描述：对方法实现的功能进行描述
     * @创建人：<a href=mailto: wujinhua@eelly.net>吴进华</a>
     * @修改人：
     * @修改时间：2014年7月24日 上午9:07:26
     * @return
     * @返回值：String
     * @异常说明：
     */
    public String value();

}
