/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.mvc.common
* @文件名：QueryVo.java
* @创建时间： 2014年11月11日 上午11:18:53
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.shiro;

import java.util.HashSet;
import java.util.Set;

/**
 * @类名：QueryVo
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年11月11日 上午11:18:53
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class QueryVo<T> {
    
    private String forRule;
    

    private Set<String> fieldList = new HashSet<String>();
    
//    private Map<String, String> paramiter = new HashMap<String, String>();
    
    private T parameter;


    
    public String getForRule() {
        return forRule;
    }

    
    public void setForRule(String forRule) {
        this.forRule = forRule;
    }
    
    public Set<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(Set<String> fieldList) {
        this.fieldList = fieldList;
    }

    public T getParameter() {
        return parameter;
    }

    public void setParameter(T parameter) {
        this.parameter = parameter;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(forRule + ":" + fieldList + ":" + parameter);
        return sb.toString().replaceAll("\\p{Cntrl}]|\\p{Space}", "_");//满足Memcache key规则
    }
    

}

