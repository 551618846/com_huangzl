/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.collection
* @文件名：TestMap.java
* @创建时间： 2015年1月27日 上午11:27:32
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.collection;

import java.util.HashMap;

/**
 * hashMap的实现是数组+entity对象,通过hash值将key索引到数组下标:index= hash(key) & length-1,数组length必须为2的n次方
 */
public class TestHashMap {

    
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");//主要难点是对key的hash值进行索引,存储到数组对应的索引位置:求索引的方法是:按位与 index= hash & length-1,结果是小于length的一个值
        
        map.get("key1");//主要难点是对key的hash值进行索引,储到数组对应的索引位置读取
        

    }

}

