/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl
* @文件名：Operator.java
* @创建时间： 2015年3月3日 下午2:03:51
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl;

import java.util.HashMap;

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
     * @throws Exception 
     * @返回值：void 
     * @异常说明：
     */
    public static void main(String[] args) throws Exception {
//        bitLeftMove();
//        bitRightMove();
        
//        bitAnd();
        bitOr();
//        hashIndex();
//        Thread.sleep(1000 * 1000);
        //65535//2进制1111111111111111(16bit)
        System.err.println(Integer.toBinaryString(65535));
    }
    
    
    //左移<<,右移>>,左移作用:移1位相当于乘以2;右移可以用来截取前n位(&可以截取后n位)
    
    public static void bitRightMove(){//右移,用来截取前n位
        int move = 3;
        int i = 19;
        int rt = i >> move;//
        System.err.println(Integer.toBinaryString(i) + "," + Integer.toBinaryString(rt));
    }
    
    public static void bitLeftMove(){//左移,常用为1 << n相当于2的n次方
        int move = 3;
        int rt = 1 << move;//对1左移n位,相当于取2的n次方
        System.err.println(rt);
        
        int doubl = 9<<1;
        System.err.println(9 +"," + doubl);
    }
    
    public static void toBinaryString(){//二进制格式
        int a = 100;
        System.err.println(Integer.toBinaryString(a));
    }
    
    
    //按位与&, 按位或|
    
    public static void bitAnd(){//按位与,用来截取后n位,或某一位
        int a = 29;
        int b = 56;
        int rt = a & b;
        
        System.err.println("与:"+Integer.toBinaryString(a)+","+Integer.toBinaryString(b)+","+Integer.toBinaryString(rt));
        
        int last = (1<<4) -1;
        int lastn = a & last;
        System.err.println("截取:"+Integer.toBinaryString(a)+","+Integer.toBinaryString(last) + "," +Integer.toBinaryString(lastn));
    }
    
    public static void bitOr(){
        int a = 29;
        int b = 56;
        int rt = a | b;
        
        System.err.println("或:"+Integer.toBinaryString(a)+","+Integer.toBinaryString(b)+","+Integer.toBinaryString(rt));
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

