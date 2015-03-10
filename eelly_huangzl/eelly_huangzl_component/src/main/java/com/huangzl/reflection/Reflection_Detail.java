/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.reflection
* @文件名：Reflection_Detail.java
* @创建时间： 2015年3月10日 下午1:52:17
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.reflection;

import java.lang.reflect.Method;

/**
 * 
 */
public class Reflection_Detail {

    /**
     * @throws Exception 
     * 
     */
    public static void main(String[] args) throws Exception {
        
//        runStaic();
//        run();
        reflection_Detail();
        
        
    }
    
    public static void reflection_Detail() throws Exception{
        Class z = Class.forName("com.huangzl.reflection.A");
        Method bar = z.getMethod("bar",String.class);
        for(int i=0;i<17;i++){
            Object rt = bar.invoke(z,"huang"+i);
//            System.out.println(rt);
        }
    }
    
    /**
     * //在class对象上,调用静态方法
     */
    public static void runStaic() throws Exception{
        Class z = Class.forName("com.huangzl.reflection.A");
        Method bar = z.getMethod("bar",String.class);
        Object rt = bar.invoke(z,"huang");//在class对象上,调用静态方法
        System.out.println(rt);
    }
    
    /**
     * //在实例对象上,调用实例方法
     */
    public static void run() throws Exception{
        Class z = Class.forName("com.huangzl.reflection.A");
        Method foo = z.getMethod("foo",String.class);
        Object obj = z.newInstance();
        Object rt1 = foo.invoke(obj,"huang");//在实例对象上,调用实例方法
        System.out.println(rt1);
    }

}

class A{
    public String foo(String name){
        System.err.println("foo.."+name);
        return "foo.." + name;
    }
    
    public static String bar(String name){
        System.err.println("bar.."+name);
        return "bar.." + name;
    }
}