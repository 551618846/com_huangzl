/** 
* @组件名：eelly_huangzl_component
* @包名：com.eelly.huangzl.member.service
* @文件名：IUserService.java
* @创建时间： 2014年10月31日 下午7:18:53
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.eelly.huangzl.member.service;

import java.util.List;
import java.util.Map;

import com.eelly.huangzl.member.model.User;

/**
 * @类名：IUserService
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年10月31日 下午7:18:53
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public interface IUserService {
    
    public List<User> findUserByCondition(Map<String, Object> map);
    
    public List<Map> findUserMapByCondition(Map<String, Object> map);
    
    public List<User> findUserPageByCondition(Map<String, Object> map,int startNo,int pageSize);

}

