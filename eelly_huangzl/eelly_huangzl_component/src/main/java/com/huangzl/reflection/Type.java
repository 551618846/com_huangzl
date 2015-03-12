/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.reflection
* @文件名：Type.java
* @创建时间： 2015年3月11日 下午2:26:58
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.reflection;

/**
 * 
 */
public class Type {

    /**
     * 
     */
    public static void main(String[] args) {
        Object[] objs = new Object[10];
        objs[0] = new Object();
        objs[1] = new Long(9);
        System.err.println(objs);
        System.err.println(new Long[1]);
        System.err.println(new String[1]);
        
        
        Object obj = new Object();
        System.err.println(obj.getClass());
        
        StringBuilder sbd = new StringBuilder("x");
        System.err.println(sbd);
    }

}

