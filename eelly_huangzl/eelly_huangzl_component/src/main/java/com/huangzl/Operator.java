/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl
* @文件名：Operator.java
* @创建时间： 2015年3月3日 下午2:03:51
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl;

/**
 * @类名：Operator
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年3月3日 下午2:03:51
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class Operator {

    /**
     * @方法名：main
     * @描述：TODO 
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2015年3月3日 下午2:03:51
     * @param args 
     * @返回值：void 
     * @异常说明：
     */
    public static void main(String[] args) {
//        bitLeftMove();
//        bitAnd();
        hashIndex();
        
    }
    
    public static void bitLeftMove(){//左移,常用为1 << n相当于2的n次方
        int move = 3;
        int rt = 1 << move;//对1左移n位,相当于取2的n次方
        System.err.println(rt);
    }
    
    public static void toBinaryString(){//二进制格式
        int a = 100;
        System.err.println(Integer.toBinaryString(a));
    }
    
    public static void bitAnd(){
        int a = 8;
        int b = 56;
        int rt = a & b;
        
        System.err.println(Integer.toBinaryString(a));
        System.err.println(Integer.toBinaryString(b));
        System.err.println(Integer.toBinaryString(rt));
    }
    
    public static void hashIndex(){//模拟hashMap的使用hash值对object进行索引(数组下标),
        int hash = 104;
        int length = 1 << 2;//必须为2的n次方// assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
        //Integer.bitCount(length);//length的二进制格式中1的个数;
        int rt = hash & length-1;//对hash的2进制值截取后n-1位
        
        System.err.println(Integer.toBinaryString(hash));
        System.err.println(Integer.toBinaryString(length-1));
        System.err.println(Integer.toBinaryString(rt));
        System.err.println(rt);
    }

}

