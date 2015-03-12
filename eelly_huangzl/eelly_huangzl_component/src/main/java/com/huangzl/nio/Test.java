/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.nio
* @文件名：Test.java
* @创建时间： 2015年3月12日 下午5:14:27
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 */
public class Test {

    /**
     * @throws Exception 
     * 
     */
    public static void main(String[] args) throws Exception {
//        path();
        file();
    }
    
    public static String path(){
        System.out.println (Test.class.getResource ("").getFile ());
        return Test.class.getResource ("").getFile ();//"/E:/git_rep/eelly_huangzl/eelly_huangzl/eelly_huangzl_component/target/classes/com/huangzl/nio/"
    }
    
    public static void file() throws Exception{
        RandomAccessFile aFile = new RandomAccessFile(path() + "data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

}

