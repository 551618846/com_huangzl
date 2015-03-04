/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.oom
* @文件名：Test.java
* @创建时间： 2015年3月4日 上午8:49:31
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * vm参数加上,生成dump
 * -Xms6M -Xmx12M -XX:PermSize=6m -XX:MaxPermSize=12m
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\
 */
public class Test {


    public static void main(String[] args) {
        test();
    }
    
    /**
     * 测试方法
     * 1,安装memory analysis
     * 2,运行配置加vm参数:-Xms6M -Xmx12M -XX:PermSize=6m -XX:MaxPermSize=12m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\
     * 3,运行,生成dump
     * 4,打开perspective:memory analysis:file>open heap dump生成分析
     * 5,打开dominator tree,按retained heap(占用内存,gc该对象时会回收的内存)排序,查看哪些对象大量占用内存;
     * shallow heap(浅堆,对象本身占用内存,不包含引用对象占用的内存)
     * retained heap(保留堆,包含引用对象占用的内存,可以理解为,gc该对象时会回收的内存)
     */
    public static void test(){//
        List<String> s = new ArrayList<String>();
        for(int i=0;i<999999;i++){
            s.add("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+i);
        }
    }
    
    public static void test1(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<999999;i++){
            sb.append("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+i);
        }
        
    }

}

