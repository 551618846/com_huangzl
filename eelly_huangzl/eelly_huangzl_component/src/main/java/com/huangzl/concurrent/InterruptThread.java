/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.concurrent
* @文件名：InterruptThread.java
* @创建时间： 2015年3月6日 下午5:28:40
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断+中断策略(即线程被中断[即调用interrupt()]时怎么处理)
 */
public class InterruptThread{

    
    public static void main(String[] args) {
        Thread x = new Thread(new IgnoreOnInterrupt());
        x.start();
        try {
            TimeUnit.NANOSECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x.interrupt();
    }

}

class StopOnInterrupt implements Runnable{
    @Override
    public void run() {
        int i = 0;
        while(!Thread.currentThread().isInterrupted()){
            //do some thing
            System.out.println(Thread.currentThread() + "do some thing.." + i++);
        }
        System.err.println(Thread.currentThread() + " isInterrupted..");
    }
}

class IgnoreOnInterrupt implements Runnable{
    @Override
    public void run() {
        int i = 0;
        for(;i<999;i++){
            //isInterrupted()不会清除状态
            //Thread.interrupted()会清除状态
            if(Thread.currentThread().isInterrupted()){
                System.err.println(Thread.currentThread() + " isInterrupted..,ignore.." + i);
                Thread.interrupted();
            }
            System.out.println(Thread.currentThread() + "do some thing.." + i);
        }
    }
}

class DoOtherOnInterrupt implements Runnable{
    @Override
    public void run() {
        
    }
}


