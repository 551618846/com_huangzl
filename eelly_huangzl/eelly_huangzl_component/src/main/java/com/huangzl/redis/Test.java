/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.redis
* @文件名：Test.java
* @创建时间： 2015年3月4日 下午4:22:24
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.redis;

import redis.clients.jedis.Jedis;


public class Test {

    //flume ng+kafka+storm+logstash+es+kibana
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port=6379;
        Jedis j = new Jedis(host, port);

        for(long i=0;i<Long.MAX_VALUE;i++){
            byte[] t = new String("11111111111111ccccccccccccccccccccccccccccccccccccccccccccc"
                    + "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"
                    + "cccccccccccccccccccccccccccccccccccccccccccccc"
                    + "cccccccccccccccccc00111223333vvvvvvvvvvvvvvvvvvvvvvvvvvv"
                    + "vvvvvvvvvvvvvvvvvmmmmxzxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxz"+i).getBytes();
            try {
                j.set(t, t);
            } catch (Exception e) {
                // TODO: handle exception
            }
            
        }
        
        j.close();
    }

}

