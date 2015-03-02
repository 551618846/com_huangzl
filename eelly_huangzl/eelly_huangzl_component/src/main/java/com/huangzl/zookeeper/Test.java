/** 
 * @组件名：eelly_huangzl_component
 * @包名：com.huangzl.zookeeper
 * @文件名：Test.java
 * @创建时间： 2015年2月27日 下午4:58:16
 * @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
 */

package com.huangzl.zookeeper;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

/**
 * @类名：Test
 * @描述:
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年2月27日 下午4:58:16
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class Test {

    /**
     * @方法名：main
     * @描述：TODO
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2015年2月27日 下午4:58:16
     * @param args
     * @throws Exception 
     * @返回值：void
     * @异常说明：
     */
    public static void main(String[] args) throws Exception {
        final String addr = "172.18.107.54:2181";
        ZooKeeper zk = null;
        zk = new ZooKeeper(addr, 20 * 1000, new Watcher() {
            
            @Override
            public void process(WatchedEvent event) {
                System.err.println("event:"+event);
                
            }
        });
        
        
        
        
        
        String path = "/huangzl";
        Stat x = zk.exists(path, false);
        if(x == null){
            String z = zk.create(path, "mydata".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.err.println("create: " + z);
        }else{
            byte[] z = zk.getData(path, false, x);
            System.err.println("getData: " + new String(z));
            zk.setData(path, new String(""+System.currentTimeMillis()).getBytes(), x.getVersion());
            System.err.println("getData2: " + new String(zk.getData(path, false, x)));
        }
        
        
        Thread.sleep(1000 * 100);

    }
    
    
    
    

}
