/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.reflection
* @文件名：Reflection_ThreadLocal.java
* @创建时间： 2015年3月10日 上午9:25:42
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.reflection;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 反射调用方法,使用到threadLocal变量,出现的问题
 * 只要是在同一个对象上(或class,静态方法),反射调用和直接调用,效果一样,
 * 反射的作用是:编译时不需要该类存在,运行时才需要该类
 * vm参数加入-XX:+TraceClassLoading参数（或者-verbose:class或者直接-verbose都行）
 * 
 */
public class Reflection_ThreadLocal {

    
    public static void main(String[] args) throws Exception {
//        Method set = ToRun.class.getMethod("set",Date.class);
        Class z = Class.forName("com.huangzl.reflection.ToRun");
        Method set = z.getMethod("set",Date.class);//和ToRun.class解耦,编译时不要ToRun.class
        set.invoke(z,new Date(0));
        
        Method get = z.getMethod("get");
        get.invoke(z);
        //↑↑↑↑↑↑↑↑↑↑ok,两次反射都是在ToRun.class上调用方法,使用同一个ThreadLocal
        
        
        ToRun.set(new Date(1000*60*60*24));
        ToRun.class.getMethod("get").invoke(ToRun.class);
        //↑↑↑↑↑↑↑↑↑↑ok,一次直接调用,一次反射调用,都是在ToRun.class上调用方法,使用同一个ThreadLocal

    }
    
    
    
    

}


class ToRun{
    private static final ThreadLocal<Date> tl = new ThreadLocal<Date>();
    
    public static void set(Date d){
        if(d != null){
            tl.set(d);
            System.err.println("init " + tl.get());
        }
    }
    
    public static void get(){
        System.err.println("get " + tl.get());
    }
}

