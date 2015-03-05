/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.order
* @文件名：BubbleSort.java
* @创建时间： 2015年3月5日 上午11:48:08
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 冒泡
 */
public class BubbleSort {
    public static void main(String[] args) {
        Integer[] a = {2,3,7,1,4,9,4,3,6,8,2};
        System.err.println(Arrays.asList(a));
        
        for(int i=0;i<a.length;i++){
            
            int j = a.length-i-1;
            for(int x=0;x<j;x++){
                if(a[x]<a[x+1]){
                    int t = a[x];
                    a[x] = a[x+1];
                    a[x+1] = t;
                }
            }
        }
        System.err.println(Arrays.asList(a));
        
    }
}

