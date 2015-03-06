/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.sort
* @文件名：QuickSort.java
* @创建时间： 2015年3月6日 上午9:51:00
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 快速排序
 * 思路:定义输入,输出,处理步骤:输入数组,处理,输出排序后的数组
 * 处理过程:快速排序:
 * 1,取数组第一个元素,作为基准值base
 * 2,迭代数组(从第二开始),对每一个元素,小于基准值的添加到左(small_list),大于等于基准值的添加到右(big_list)
 * 3,对small_list和big_list分别执行嵌套排序
 * 4,合并small_list,base,big_list,此时是一个排序后的数组,返回
 */
public class QuickSort {
    public static void main(String[] args) {
        Integer[] a = {5,4,6,7,2,1,8,9,0,7,6,4,3};
        System.err.println(Arrays.asList(a));
        
        
        System.err.println(quickSort(a));;
    }
    
    static List<Integer> quickSort(Integer[] a){
        List<Integer> sort = new ArrayList<Integer>();
        
        if(a.length>1){
            int base = a[0];
            List<Integer> sm = new ArrayList<Integer>();
            List<Integer> big = new ArrayList<Integer>();
            
            for(int i=1;i<a.length;i++){
                if(a[i]<base){
                    sm.add(a[i]);
//                    System.err.println("sm list:"+sm);
                }else{
                    big.add(a[i]);
//                    System.err.println("big list:"+big);
                }
            }
            List<Integer> sort_sm = quickSort(sm.toArray(new Integer[0]));
            List<Integer> sort_big = quickSort(big.toArray(new Integer[0]));
            
            sort.addAll(sort_sm);
            sort.add(base);
            sort.addAll(sort_big);
            
            return sort;
        }else if (a.length==1){
//            System.err.println(a[0]);
            List<Integer> t = new ArrayList<Integer>();
            t.add(a[0]);
            return t;
        }else{
            return new ArrayList<Integer>();
        }
    }

}

