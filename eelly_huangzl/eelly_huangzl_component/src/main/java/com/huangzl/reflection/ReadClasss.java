/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.reflection
* @文件名：ReadClasss.java
* @创建时间： 2015年3月11日 上午11:51:43
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.reflection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 
 */
public class ReadClasss {

    /**
     * @throws Exception 
     * 
     */
    public static void main(String[] args) throws Exception {
        File file = new File("E:\\git_rep\\eelly_huangzl\\eelly_huangzl\\eelly_huangzl_component\\target\\classes\\com\\huangzl\\reflection\\Simple.class");
        
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        String s = null;
        while((s=br.readLine())!=null){
            System.err.println(s);
        }
    }

}

class Simple{
    public String x = "x";
}
