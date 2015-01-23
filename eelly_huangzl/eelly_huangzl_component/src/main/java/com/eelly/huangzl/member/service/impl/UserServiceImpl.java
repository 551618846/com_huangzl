/** 
* @组件名：eelly_huangzl_component
* @包名：com.eelly.huangzl.user.service.impl
* @文件名：UserServiceImpl.java
* @创建时间： 2014年9月21日 上午11:35:20
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.eelly.huangzl.member.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eelly.core.dao.IBaseMapper;
import com.eelly.core.exception.ServiceException;
import com.eelly.huangzl.member.dao.UserMapper;
import com.eelly.huangzl.member.model.User;
import com.eelly.huangzl.member.model.UserExample;
import com.eelly.huangzl.member.service.IUserService;

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
public class UserServiceImpl implements IUserService{
    

    @Resource
    private UserMapper userMapper;

    
    @Override
    public List<User> findUserByCondition(Map<String, Object> map) {
        return userMapper.findUserByCondition(map);
    }

    @Override
    public List<Map> findUserMapByCondition(Map<String, Object> map) {
        return userMapper.findUserMapByCondition(map);
    }

    
    @Override
    public List<User> findUserPageByCondition(Map<String, Object> map, int startNo, int pageSize) {
        return userMapper.findUserPageByCondition(map, startNo, pageSize);
    }
    
    

}

