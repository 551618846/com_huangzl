/** 
 * @组件名：eelly_core
 * @包名：com.eelly.core.test
 * @文件名：BaseTestCase.java
 * @创建时间： 2014年5月28日 下午4:58:46
 * @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
 */

package com.eelly.huangzl.core;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runner.RunWith;
import org.junit.runners.model.FrameworkMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @类名：BaseTestCase
 * @描述: 测试基类
 * @创建人：<a href=mailto:huangweiqi@eelly.net>huangweiqi</a>
 * @修改人：
 * @修改时间：2014年5月28日 下午4:58:46
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)//,"classpath*:/conf/**/applicationContext-global.xml","classpath*:/conf/**/applicationContext-core.xml"
@ContextConfiguration({ "classpath*:/conf/**/junit.xml" })
public abstract class BaseTestCase {

    /**
     * @方法名：beforeClass
     * @描述：在测试类运行之前调用的方法，只调用一次
     * @创建人：<a href=mailto: huangweiqi@eelly.net>huangweiqi</a>
     * @修改人：
     * @修改时间：2014年5月28日 下午5:01:51
     * @返回值：void
     * @异常说明：
     */
    @BeforeClass
    public static void beforeClass() {

    }

    /**
     * @方法名：before
     * @描述：在每个测试用例方法之前都调用
     * @创建人：<a href=mailto: huangweiqi@eelly.net>huangweiqi</a>
     * @修改人：
     * @修改时间：2014年5月28日 下午5:04:52
     * @返回值：void
     * @异常说明：
     */
    @Before
    public void before() {
        System.out.println("start test --> ");
    }

    @Rule
    public MethodRule watchman = new TestWatchman() {

        public void starting(FrameworkMethod method) {
            System.out.println("\n\n开始测试: " + method.getName());

        }
    };

    /**
     * @方法名：after
     * @描述：在每个测试用例方法之后都调用
     * @创建人：<a href=mailto: huangweiqi@eelly.net>huangweiqi</a>
     * @修改人：
     * @修改时间：2014年5月28日 下午5:05:30
     * @返回值：void
     * @异常说明：
     */
    @After
    public void after() {
        System.out.println("end test --> \n\n");
    }
}
