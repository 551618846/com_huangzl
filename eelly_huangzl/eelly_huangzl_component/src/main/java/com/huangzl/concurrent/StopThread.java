/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.concurrent
* @文件名：StopThread.java
* @创建时间： 2015年3月6日 下午4:11:49
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 错误的做法
 */
public class StopThread implements Runnable{
    public static void main(String[] args) {
        /*StopThread x = new StopThread();
        new Thread(x).start();
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            x.stopMe();
        }*/
        
        StopThread1 x = new StopThread1();
        x.start();
        
        try {
//            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            x.stopMe();
        }
        
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            System.err.println(Thread.currentThread() + "do someth..");//Thread[Thread-0,5,main]do someth..
        }
    }
    
    public void stopMe(){
        System.err.println(Thread.currentThread() + " stop..");//Thread[main,5,main] stop..
        Thread.currentThread().interrupt();
    }

}


/**
 * 正确做法
 */
class StopThread1 extends Thread{
    CountDownLatch c = new CountDownLatch(1);
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            System.err.println(Thread.currentThread() + "do someth..");//Thread[Thread-0,5,main]do someth..
            c.countDown();
        }
    }
    
    public void stopMe(){
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(this + " stop..");//Thread[main,5,main] stop..
        this.interrupt();
    }
}