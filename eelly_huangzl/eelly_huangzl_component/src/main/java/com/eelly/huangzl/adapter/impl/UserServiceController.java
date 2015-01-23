/** 
* @组件名：eelly_huangzl_component
* @包名：com.eelly.huangzl.adapter.impl
* @文件名：UserServiceController.java
* @创建时间： 2014年9月19日 下午3:14:14
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.eelly.huangzl.adapter.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.thrift.TException;

import com.eelly.core.exception.ServiceException;
import com.eelly.core.thrift.annotation.ThriftService;
import com.eelly.core.thrift.vo.Params;
import com.eelly.core.thrift.vo.ReturnObj;
import com.eelly.huangzl.adapter.user.UserService;

/**
 * @类名：UserServiceController
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年9月19日 下午3:14:14
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
@ThriftService
public class UserServiceController extends com.eelly.core.thrift.adapter.impl.CommonController implements UserService.Iface{

    @Resource
    private com.eelly.huangzl.member.service.IUserService userService;

    /**
      * <p>主题：getUserByUserName</p>
      * <p>描述： </p>
      * @param userName
      * @param params
      * @return
      * @throws TException
      * @see com.eelly.huangzl.adapter.user.UserService.Iface#getUserByUserName(java.lang.String, com.eelly.core.thrift.vo.Params)
      */
    @Override
    public ReturnObj getUserByUserName(String userName, Params params) throws TException {
        // TODO Auto-generated method stub
        return invokeService(new InvokeServiceCallback() {

            @Override
            public void serviceMethodBefore() {

            }

            @Override
            public void serviceMethod() throws ServiceException {
                String name = "";
                HashMap map = new HashMap();
                userService.findUserByCondition(map);
            }

            @Override
            public void serviceMethodAfter() {

            }
        });
    }

}

