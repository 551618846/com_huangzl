package com.eelly.huangzl.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eelly.core.dao.IBaseMapper;
import com.eelly.huangzl.member.model.User;
import com.eelly.huangzl.member.model.UserExample;



public interface UserMapper  extends IBaseMapper<User, UserExample>{
    
    public List<User> findUserByCondition(@Param("map") Map<String, Object> map);
    
    public List<User> findUserPageByCondition(@Param("map") Map<String, Object> map,@Param("startNo") int startNo,@Param("pageSize") int pageSize);
    
    public List<Map> findUserMapByCondition(@Param("map") Map<String, Object> map);
    
}