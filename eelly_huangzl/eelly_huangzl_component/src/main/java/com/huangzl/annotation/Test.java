/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.annotation
* @文件名：Test.java
* @创建时间： 2014年9月26日 下午2:23:57
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.eelly.core.util.ReflectionUtil;
import com.eelly.core.util.annotation.EellyAnnotation4Method;

/**
 * @类名：Test
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年9月26日 下午2:23:57
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class Test {

    /**
     * @方法名：main
     * @描述：TODO 
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2014年9月26日 下午2:23:57
     * @param args 
     * @返回值：void 
     * @异常说明：
     */
    public static void main(String[] args) {
        String serviceName = "com.eelly.huangzl.user.service.IUserService";
        String realMethodName = "getUserByUserName";
        
        Class<?> serviceClz = ReflectionUtil.getClassByClassName(serviceName); // Service Class
//        Class<?> serviceClz = IUserService.class; // Service Class
        
        Method[] ls = serviceClz.getDeclaredMethods();
        for(Method method : ls){
            Annotation[] annotations = method.getDeclaredAnnotations();
            if (annotations == null) {
                
            }
            // 遍历方法上的注解
            for (Annotation annotation : annotations) {
                if (annotation instanceof EellyAnnotation4Method) {
                    System.out.println(((EellyAnnotation4Method) annotation).value());
                }
            }
        }
        
//        Method method = null;
//        try {
//            // 获取方法对象
//            method = serviceClz.getDeclaredMethod(realMethodName);
//        } catch (NoSuchMethodException | SecurityException e) {
//            throw new RuntimeException(e);
//        }

        

    }

}

