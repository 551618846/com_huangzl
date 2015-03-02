/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl
* @文件名：StringTest.java
* @创建时间： 2015年2月3日 下午1:46:55
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl;

/**
 * @类名：StringTest
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年2月3日 下午1:46:55
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class StringTest {

    /**
     * @方法名：main
     * @描述：TODO 
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2015年2月3日 下午1:46:55
     * @param args 
     * @返回值：void 
     * @异常说明：
     */
    public static void main(String[] args) {
        String a = new String("a");
        String b = "a";
        String c = "a";
        
        System.err.println(a == b);//false
        System.err.println(b == c);//true
    }

}

