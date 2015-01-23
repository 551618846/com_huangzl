/** 
* @组件名：eelly_huangzl_component
* @包名：com.eelly.huangzl.user.service.impl
* @文件名：UserServiceImpl.java
* @创建时间： 2014年9月21日 上午11:35:20
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.eelly.huangzl.springtx.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import com.eelly.huangzl.springtx.dao.UserMapper2;
import com.eelly.huangzl.springtx.model.User;
import com.eelly.huangzl.springtx.service.IUserService2;



/**
 * @类名：UserServiceImpl
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年9月21日 上午11:35:20
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
@Service
public class UserServiceImpl2 implements IUserService2{
    

    @Autowired
    private UserMapper2 userMapper;
    
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor=Exception.class)
    public void test_WRITE_DEFAULT() throws Exception{
        StopWatch watch = new StopWatch();
        watch.start();
        
        String u_name = "huangzl";
        User u = userMapper.getUserByName(u_name).get(0);
        System.err.println("test_WRITE_DEFAULT getUserByName : " + u.getuPass());
        
        u.setuPass(new Date().getTime()+"");
        System.err.println("test_WRITE_DEFAULT set to : " + u.getuPass());
        userMapper.updateByPrimaryKey(u);
        
        watch.stop();
        System.err.println("test_WRITE_DEFAULT time:" + watch.getTotalTimeSeconds());
        
//        Thread.sleep(1000 * 30);
    }
    
    
    
    
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor=Exception.class)
    public void test_READ_UNCOMMITTED() throws Exception{
        String u_name = "huangzl";
        User u = userMapper.getUserByName(u_name).get(0);
        System.err.println("getUserByName norm:"+u.getuPass());
        
        try {
            Thread.sleep(1000 * 4);//等待其他事务修改记录
        } catch (Exception e) {
        }
        
        User u2 = userMapper.getUserByName(u_name).get(0);
        System.err.println("getUserByName dirty:"+u2.getuPass());//脏读
        
        try {
            Thread.sleep(1000 * 10);//等待其他事务回滚
        } catch (Exception e) {
        }
        
        User u3 = userMapper.getUserByName(u_name).get(0);
        System.err.println("getUserByName norm again:"+u3.getuPass());
    }
    
    @Override
    @Transactional(isolation = Isolation.DEFAULT, rollbackFor=Exception.class)
    public void testWrite_READ_UNCOMMITTED() throws Exception{
        String u_name = "huangzl";
        User u = userMapper.getUserByName(u_name).get(0);
        
        u.setuPass(new Date().getTime()+"");
        System.err.println("testWrite_READ_UNCOMMITTED set to : " + u.getuPass());
        userMapper.updateByPrimaryKey(u);
        
        Thread.sleep(1000 * 5);
        
        System.err.println("testWrite_READ_UNCOMMITTED rollback");
        throw new RuntimeException("test RuntimeException");
        
    }
    
    
    
    
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)//修改线程
    public void test_READ_COMMITTED() throws Exception{//写锁行,不锁整表;读不锁行,多次读结果不同;
        String u_name = "huangzl";
        User u = userMapper.getUserByName(u_name).get(0);
        System.err.println("test_READ_COMMITTED getUserByName : " + u.getuPass());
        
        
        Thread.sleep(1000 * 10);
        
        User u2 = userMapper.getUserByName(u_name).get(0);
        System.err.println("test_READ_COMMITTED getUserByName : " + u2.getuPass());
        
    }
    
    
    
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor=Exception.class)
    public void test_REPEATABLE_READ() throws Exception{//读不锁行;多次读结果相同(其他事务修改被忽略)!!!!
        String u_name = "huangzl";
        User u = userMapper.getUserByName(u_name).get(0);
        System.err.println("test_REPEATABLE_READ getUserByName : " + u.getuPass());
        
        Thread.sleep(1000 * 10);
        
        User u2 = userMapper.getUserByName(u_name).get(0);
        System.err.println("test_REPEATABLE_READ getUserByName : " + u2.getuPass());
        
    }
    
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor=Exception.class)//
    public void phantomRead() throws Exception{//幻读:本事务前后两次读取相同一批记录,记录却不同(个数不同;某个记录不同?)
        String u_name = "huangzl";
        List<User> list = userMapper.getUserByName(u_name);
        int i=0;
        for(User u : list){
            System.err.println(i++ + " phantomRead getUserByName:"+u.getuPass());
        }
        
        Thread.sleep(1000 * 30);//等待其他事务修改记录
        
        int j=0;
        list = userMapper.getUserByName(u_name);
        for(User u : list){
            System.err.println(j++ + "phantomRead __getUserByName:"+u.getuPass());
        }
    }
    
    
    @Override
    @Transactional(isolation = Isolation.DEFAULT, rollbackFor=Exception.class)
    public void testInsert_phantomRead() throws Exception{
        StopWatch watch = new StopWatch();
        watch.start();
        
        String u_name = "huangzl";
        User u = new User();
        u.setuName(u_name);
        u.setuPass(new Date().getTime()+"");
        

        System.err.println("testInsert_phantomRead start insert: " + u.getuPass());
        userMapper.insertSelective(u);
        
        watch.stop();
        System.err.println("testInsert_phantomRead time:" + watch.getTotalTimeSeconds());
        
    }
    
    
    
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor=Exception.class)//修改线程
    public void test_SERIALIZABLE() throws Exception{//锁表
        String u_name = "huangzl";
        List<User> list = userMapper.getUserByName(u_name);
        int i=0;
        for(User u : list){
            System.err.println(i++ + " phantomRead getUserByName:"+u.getuPass());
        }
        
        Thread.sleep(1000 * 20);//等待其他事务修改记录
        
        int j=0;
        list = userMapper.getUserByName(u_name);
        for(User u : list){
            System.err.println(j++ + "phantomRead __getUserByName:"+u.getuPass());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}

