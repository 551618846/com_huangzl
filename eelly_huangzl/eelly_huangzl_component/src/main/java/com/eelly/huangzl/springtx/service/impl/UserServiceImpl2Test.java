/** 
* @组件名：eelly_huangzl_component
* @包名：com.eelly.huangzl.springtx.service.impl
* @文件名：UserServiceImpl2Test.java
* @创建时间： 2015年1月20日 下午4:01:57
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.eelly.huangzl.springtx.service.impl;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.eelly.huangzl.core.BaseTestCase;
import com.eelly.huangzl.springtx.model.User;
import com.eelly.huangzl.springtx.service.IUserService2;
import com.mysql.jdbc.DatabaseMetaData;


/**
 * @类名：UserServiceImpl2Test
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2015年1月20日 下午4:01:57
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class UserServiceImpl2Test  extends BaseTestCase{
    
    @Resource
    private IUserService2 userService;
    
    /****spring事务隔离级别*****/
    
    /**
     * 
     * (脏读即可能读到其他事务未提交的数据)(不可重复读即两次读取相同记录可能读到不同结果)(幻读即两次读取可能读到不同记录数)
     * 
     * READ_UNCOMMITTED:本级别的事务,能读取其他事务未提交的修改
     * 
     * ISOLATION_READ_COMMITTED:本级别的事务,能读取其他事务已提交的修改
     * 
     * ISOLATION_REPEATABLE_READ:本级别的事务,会忽略其他事务已提交的修改
     * 
     * SERIALIZABLE:本级别的事务,会锁表
     */
    
    /**
     * 测试过程:开启两个线程,模拟两个事务
     * 测试场景(mybatis的select语句需要设置flushCache="true")
     * 1,脏读:第1事务隔离级别设置为READ_UNCOMMITTED,第2事务随意:第1事务读取一次,休眠(这时第2事务修改后休眠),第1事务读取第二次(脏读)休眠,第2事务回滚,第1事务读取3次;test_READ_UNCOMMITTED+testWrite_READ_UNCOMMITTED
     * 2,不可重复读:1事务隔离级别设置为READ_COMMITTED,第2事务随意:第1事务读取一次,休眠(这时第2事务修改后提交),第1事务读取第二次(结果不同);test_READ_COMMITTED+test_WRITE_DEFAULT
     * 3,可重复读:1事务隔离级别设置为REPEATABLE_READ,第2事务随意,测试同上,第1事务读取第二次(结果相同);test_REPEATABLE_READ+test_WRITE_DEFAULT
     * 3,(不会?)幻读:1事务隔离级别设置为REPEATABLE_READ,第2事务随意:第1事务读取一次,休眠(这时第2事务insert后提交),第1事务读取第二次(记录数不同)phantomRead+testInsert_phantomRead
     * 4,acid:1事务隔离级别设置为SERIALIZABLE,第2事务随意:第1事务开始,休眠,第1事务修改后提交等待第1事务;test_SERIALIZABLE+testInsert_phantomRead
     */
    @Test
    public void mytest_dirtyRead() throws Exception{
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    userService.test_READ_UNCOMMITTED();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        Thread.sleep(1000 * 1);
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    userService.testWrite_READ_UNCOMMITTED();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        Thread.sleep(1000 * 300);
    }
    
    /**
     * 不可重复读
     */
    @Test
    public void mytest_nonRepRead() throws Exception{
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    userService.test_READ_COMMITTED();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        Thread.sleep(1000 * 2);
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    userService.test_WRITE_DEFAULT();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        Thread.sleep(1000 * 300);
    }
    
    /**
     * 可重复读
     */
    @Test
    public void mytest_repRead() throws Exception{
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    userService.test_REPEATABLE_READ();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        Thread.sleep(1000 * 2);
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    userService.test_WRITE_DEFAULT();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        Thread.sleep(1000 * 300);
    }
    
    
    /**
     * 幻读//测试结果不会幻读?忽略insert的数据
     */
    @Test
    public void mytest_phantomRead() throws Exception{
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    userService.phantomRead();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        Thread.sleep(1000 * 2);
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    userService.testInsert_phantomRead();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        Thread.sleep(1000 * 300);
    }
    
    
    /**
     * acid
     */
    @Test
    public void mytest_SERIALIZABLE() throws Exception{
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    userService.test_SERIALIZABLE();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        Thread.sleep(1000 * 2);
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    userService.testInsert_phantomRead();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        Thread.sleep(1000 * 300);
    }
    
    

}

