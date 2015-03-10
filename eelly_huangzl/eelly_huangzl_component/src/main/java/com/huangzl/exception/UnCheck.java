/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.exception
* @文件名：UnCheck.java
* @创建时间： 2015年3月10日 下午2:14:53
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.exception;

/**
 * 
 */
public class UnCheck {

    /**
     * 
     */
    public static void main(String[] args) {
//        checkEx();//编译错误,check异常必须处理
        
        try {
            checkEx();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void noneEx() throws CheckEx{//异常可以随便跑
        
    }
    
    public static void checkEx() throws CheckEx{//抛出异常都是throw new xxException()
        throw new CheckEx();
    }

}

class CheckEx extends Exception{
    CheckEx(){
        System.out.println("CheckEx");
    }
}