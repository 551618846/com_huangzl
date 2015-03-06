/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.concurrent
* @文件名：RunningFixTime.java
* @创建时间： 2015年3月6日 下午2:10:01
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 运行一段时间后,终止线程
 */
public class RunningFixTime {
    public static void main(String[] args){
        
        Runner x = new Runner();
        x.start();
        
        
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e1) {
            e1.printStackTrace();
        }finally{
            x.stopMe();
        }
        
    }
    
    
    

}


class Runner extends Thread{
    
    @Override
    public void run() {
        
        
        System.err.println("+++++++++++++");
        while(!Thread.currentThread().isInterrupted()){
            System.err.println(System.currentTimeMillis());
        }
    }
    
    public void stopMe(){
        System.err.println("=========================================");
        this.interrupt();
    }
    
}
