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
 * @类名：TestMap
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年1月27日 上午11:27:32
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class TestHashMap {

    /**
     * @方法名：main
     * @描述：TODO 
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2015年1月27日 上午11:27:33
     * @param args 
     * @返回值：void 
     * @异常说明：
     */
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");//主要难点是对key的hash值进行索引,存储到数组对应的索引位置:求索引的方法是:按位与 index= hash & length-1,结果是小于length的一个值
        
        map.get("key1");//主要难点是对key的hash值进行索引,储到数组对应的索引位置读取
        

    }

}

