/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.concurrent
* @文件名：CountDown.java
* @创建时间： 2015年3月6日 下午4:03:26
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch等待
 */
public class CountDown {
    public static void main(String[] args) {
        
        final CountDownLatch threadGo = new CountDownLatch(1);
        
        int count = 10;
        final CountDownLatch threadDone = new CountDownLatch(count);
        for(int i=0;i<count;i++){
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    try {
                        threadGo.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println(Thread.currentThread() + " go..");
                    
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println(Thread.currentThread() + " done..");
                    threadDone.countDown();
                }
            }).start();
        }
        
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + " ready...");
        threadGo.countDown();
        
        
        try {
            threadDone.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + " all task done...");
        
    }

}

