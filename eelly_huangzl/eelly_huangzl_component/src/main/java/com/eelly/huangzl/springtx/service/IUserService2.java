/** 
* @组件名：eelly_huangzl_component
* @包名：com.eelly.huangzl.member.service
* @文件名：IUserService.java
* @创建时间： 2014年10月31日 下午7:18:53
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.eelly.huangzl.springtx.service;





/**
 * @类名：IUserService
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年10月31日 下午7:18:53
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public interface IUserService2 {
    

    public void test_WRITE_DEFAULT() throws Exception;
    
    public void test_READ_UNCOMMITTED() throws Exception;
    
    public void testWrite_READ_UNCOMMITTED() throws Exception;
    
    public void test_READ_COMMITTED() throws Exception;
    
    public void test_REPEATABLE_READ() throws Exception;
    
    public void phantomRead() throws Exception;
    public void testInsert_phantomRead() throws Exception;
    
    public void test_SERIALIZABLE() throws Exception;//锁表
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

