/** 
* @组件名：eelly_huangzl_component
* @包名：com.eelly.huangzl.member.service.impl
* @文件名：UserServiceImplTest.java
* @创建时间： 2014年10月31日 下午7:42:04
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.eelly.huangzl.member.service.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.eelly.core.test.BaseTestCase;
import com.eelly.huangzl.member.model.User;
import com.eelly.huangzl.member.service.IUserService;


/**
 * @类名：UserServiceImplTest
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年10月31日 下午7:42:04
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class UserServiceImplTest extends BaseTestCase{
    
    @Resource
    private IUserService userService;

    /**
     * Test method for {@link com.eelly.huangzl.member.service.impl.UserServiceImpl#findUserByCondition(java.util.Map)}.
     */
    @Test
    public void testFindUserByCondition() {
        /*HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("u_name", "huangzl");
        List<User> list = userService.findUserByCondition(map);
//        List<Map> list = userService.findUserMapByCondition(map);
        System.out.println(list);
        */
        try {
            Thread.sleep(1000 * 1000000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFindUserPageByCondition() {
        int startNo = 0;
        int pageSize = 5;
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("u_name", "huangzl");
        List<User> list = userService.findUserPageByCondition(map,startNo,pageSize);
        System.out.println(list);
    }

}

