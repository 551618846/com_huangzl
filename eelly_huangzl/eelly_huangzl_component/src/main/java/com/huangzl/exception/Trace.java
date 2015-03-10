/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.exception
* @文件名：Trace.java
* @创建时间： 2014年9月24日 下午5:14:56
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.exception;

/**
 * @类名：Trace
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年9月24日 下午5:14:56
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class Trace {

    /**
     * 
     */
    public static void main(String[] args) {
        Test.source();

    }
    
    

}


class Test{
    public static void source(){//获取将来调用本方法的类名和方法名
        StackTraceElement element = new Throwable().getStackTrace()[1];//1!!!
        String className = element.getClassName();
        String methodName =  element.getMethodName();
        System.out.println("throws at " + className + "--" + methodName);
        
        
        StackTraceElement[] ls = new Throwable().getStackTrace();
        for(StackTraceElement s : ls){
            String classN = s.getClassName();
            String methodN =  s.getMethodName();
            System.out.println("throws : " + classN + "--" + methodN);
        }
    }
}

