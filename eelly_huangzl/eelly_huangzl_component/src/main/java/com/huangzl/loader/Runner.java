/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.loader
* @文件名：Runner.java
* @创建时间： 2014年11月4日 下午3:31:26
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.loader;

import java.io.File;

/**
 * @类名：Runner
 * @描述: 使用自定义的ClassLoader动态重新加载class.注意一个ClassLoader实例下只能加载一个类一次,即Class是根据它的全名（包名+类名）和加载它的 ClassLoader来唯一标识的.
 * 所以重新加载类必须new一个ClassLoader.并且Cast的时候,需要用父类或接口上转型(因为两次ClassLoader加载的MyObject的Class不同,不能cast.父类或接口不能ClassLoader两次).
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年11月4日 下午3:31:26
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class Runner {

    /**
     * @方法名：main
     * @描述：TODO 
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2014年11月4日 下午3:31:26
     * @param args 
     * @throws Exception 
     * @返回值：void 
     * @异常说明：
     */
    public static void main(String[] args) throws Exception {
        ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
        MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
        Class myObjectClass = classLoader.loadClass("com.huangzl.loader.MyObject");

        MyObjectInterface object1 = (MyObjectInterface) myObjectClass.newInstance();
        object1.print();
        Thread.sleep(1000*2);//睡眠,修改MyObject,稍后重新加载

//        MyObjectSuperClass object2 = (MyObjectSuperClass) myObjectClass.newInstance();

        // create new class loader so classes can be reloaded.
        classLoader = new MyClassLoader(parentClassLoader);//新建一个ClassLoader才能再次加载一个类
        myObjectClass = classLoader.loadClass("com.huangzl.loader.MyObject");

        object1 = (MyObjectInterface) myObjectClass.newInstance();
//        object2 = (MyObjectSuperClass) myObjectClass.newInstance();
        object1.print();
        
        
        System.out.println(new File("").getAbsolutePath());//D:\workspace\eelly_huangzl\eelly_huangzl_component
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        System.out.println(Class.class.getClass().getResource("/").getPath());
    }

}

