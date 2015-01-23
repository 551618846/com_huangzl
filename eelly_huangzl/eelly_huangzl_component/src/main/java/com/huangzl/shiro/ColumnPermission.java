/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.mvc.common
* @文件名：ColumnPermission.java
* @创建时间： 2014年11月14日 上午10:22:53
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.shiro;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.Permission;

/**
 * @类名：ColumnPermission
 * @描述: 列权限
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年11月14日 上午10:22:53
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class ColumnPermission implements Permission, Serializable{
    
    private static final long serialVersionUID = 2235427348558898145L;
//    protected static final String PART_DIVIDER_TOKEN = ":";
//    protected static final String SUBPART_DIVIDER_TOKEN = ",";
    
    private Set<String> columns;
    private String permissionName;
    
    public String getPermissionName(){
        return this.permissionName;
    }
    
    /**
     * @方法名：getColumns
     * @描述：用于获取该列权限下的列名 
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2014年11月14日 下午4:43:44
     * @return 
     * @返回值：Set<String> 
     * @异常说明：
     */
    public Set<String> getColumns() {
        return columns;
    }
    
    /**
     * 
      * 创建一个新的实例 ColumnPermission. 
      * <p>主题： </p>
      * <p>描述： 主要是调用shiroAPI时创建对象ColumnPermission(String permissionName)传入</p>
      * @param permissionName
     */
    public ColumnPermission(String permissionName){
        this.permissionName = permissionName;
        if(StringUtils.isBlank(permissionName)){
            throw new IllegalArgumentException("permissionName cannot blank.");
        }
    }

    /**
     * 
      * 创建一个新的实例 ColumnPermission. 
      * <p>主题： </p>
      * <p>描述： shiro加载权限时创建完整对象存入shiro缓存</p>
      * @param permissionName
      * @param columns
     */
    public ColumnPermission(String permissionName,Set<String> columns) {
        this.columns = columns;
        this.permissionName = permissionName;
        
        if(StringUtils.isBlank(permissionName)){
            throw new IllegalArgumentException("permissionName cannot blank.");
        }
        
        /*if (columns == null || columns.isEmpty()) {
            throw new IllegalArgumentException("columns cannot blank.");
        }*/
    }
    
    /**
     * 
      * <p>主题：implies</p>
      * <p>描述：必需调用SecurityUtils.getSubject().isPermitted(new ColumnPermission("findUserByQueryVo-name"))来匹配 </p>
      * @param p
      * @return
      * @see org.apache.shiro.authz.Permission#implies(org.apache.shiro.authz.Permission)
     */
    public boolean implies(Permission p){
        //调用SecurityUtils.getSubject().isPermitted(String)是,默认是把String封装成一个WildcardPermission(String, false)传过来匹配!!!
        //所以必需调用SecurityUtils.getSubject().isPermitted(new ColumnPermission("findUserByQueryVo-name"))
        if (!(p instanceof ColumnPermission)) {
            return false;
        }
        
        ColumnPermission tt = (ColumnPermission) p;
        String name = tt.getPermissionName();
        
        if(!StringUtils.isBlank(name) && name.equals(permissionName)){
            return true;
        }
        
        
        
        return false;
    }
    
    
    
    public String toString() {
        return permissionName + ":" + columns;
    }

    public boolean equals(Object o) {
        if (o instanceof ColumnPermission) {
            ColumnPermission cp = (ColumnPermission) o;
            String name = cp.getPermissionName();
            if(!StringUtils.isBlank(name) && columns != null && name.equals(permissionName)){
                return columns.equals(cp.getColumns());
            }
            
        }
        return false;
    }

    public int hashCode() {
        return permissionName.hashCode();
    }

}

