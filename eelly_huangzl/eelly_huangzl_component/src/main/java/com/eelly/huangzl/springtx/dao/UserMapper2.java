package com.eelly.huangzl.springtx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.eelly.core.dao.IBaseMapper;
import com.eelly.huangzl.springtx.model.User;
import com.eelly.huangzl.springtx.model.UserExample;



public interface UserMapper2  extends IBaseMapper<User, UserExample>{
    
    @Options(flushCache=true)
    public List<User> getUserByName(@Param("u_name")String u_name);
    
    /*public List<User> findUserByCondition(@Param("map") Map<String, Object> map);
    
    public List<User> findUserPageByCondition(@Param("map") Map<String, Object> map,@Param("startNo") int startNo,@Param("pageSize") int pageSize);
    
    public List<Map> findUserMapByCondition(@Param("map") Map<String, Object> map);*/
    
}