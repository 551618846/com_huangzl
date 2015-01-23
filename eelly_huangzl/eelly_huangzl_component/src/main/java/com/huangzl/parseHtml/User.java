/** 
* @组件名：eelly_huangzl_component
* @包名：com.huangzl.parseHtml
* @文件名：User.java
* @创建时间： 2014年9月29日 上午9:05:26
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.parseHtml;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @类名：User
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年9月29日 上午9:05:26
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class User {

    /**
     * @方法名：main
     * @描述：TODO 
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2014年9月29日 上午9:05:27
     * @param args 
     * @throws Exception 
     * @返回值：void 
     * @异常说明：
     */
    public static void main(String[] args) throws Exception {
        getAll();
        
    }
    
    
    public static HashMap<String,String> getAll(){
        String surl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/";
        
        
        String index = getHtml(surl);
        
        HashMap<String,String> all = new HashMap<String, String>();
        
        
        HashMap<String,String> pr = findProvin(index);
        
        all.putAll(pr);
        
        
        Set<String> keys = pr.keySet();
        for(String s : keys){
            if(!"12".equals(s)){
                //continue;
            }
            if(!"13".equals(s)){
//                continue;
            }
            String prUrl = surl + s +".html";//http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/44.html
            System.out.println(prUrl);
            
            String prHtml = getHtml(prUrl);
            HashMap<String,String> city = findCity(prHtml);
//            pr.putAll(city);//循环过程不能修改
            all.putAll(city);
            
            
            Set<String> cityKeys = city.keySet();
            
            for(String ck : cityKeys){
                String cityUrl = surl + s + "/" + ck.substring(0, 4) + ".html";//http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/44/4401.html
                System.out.println(cityUrl);
                
                
                String cityHtml = getHtml(cityUrl);
                HashMap<String,String> qu = findQu(cityHtml);
                
                all.putAll(qu);
                
                System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
                Set<String> quKeys = qu.keySet();
                for(String qk : quKeys){
                    String quUrl = surl + s + "/" + ck.substring(2, 4) + "/" + qk.substring(0, 6) + ".html";//http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/44/01/440113.html
                    System.out.println(quUrl);
                    String QuHtml = getHtml(quUrl);
                    HashMap<String,String> jd = findJD(QuHtml);
                    
                    all.putAll(jd);
                }
                System.out.println("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
            }
            
            
            
        }
        
        System.out.println();
        System.out.println(all.size());
        System.out.println(all);
        
        return all;
        
        /*String prUrl_ = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/44.html";
        String prHtml = getHtml(prUrl_);
        findCity(prHtml);*/
        
        /*String cityUrl_ = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/44/4401.html";
        String cityHtml = getHtml(cityUrl_);
        findQu(cityHtml);*/
        
        /*String quUrl_ = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/44/01/440113.html";
        String QuHtml = getHtml(quUrl_);
        findJD(QuHtml);*/
    }
    
    static HashMap<String,String> findJD(String input){//区下面的 街道 或 镇//
        HashMap<String, String> rt = new HashMap<String, String>();
        
        String reg = "<tr class='towntr'>(.)+?</tr>";//考虑两类
        
        Pattern mob = Pattern.compile(reg);
        Matcher m = mob.matcher(input);
        
        while(m.find()){
            int st = m.start();
            int ed = m.end();
            String g = m.group();
            
//            System.out.println(st+"~"+ed+" : "+g);
            
            HashMap<String, String> t = findJdTD1(g);
            HashMap<String, String> t2 = findJdTD2(g);
            rt.putAll(t);
            rt.putAll(t2);
        }
//        System.out.println(rt);
        return rt;
    }
    
    
    static HashMap<String,String> findJdTD2(String input){//分两类
      //<td>440113007000</td><td>市桥街道</td> || <td><a href='13/440113007.html'>440113007000</a></td><td><a href='13/440113007.html'>市桥街道</a></td>
        HashMap<String, String> rt = new HashMap<String, String>();
        
//        String reg = "<td><a href='((.)+?)?\\.html'>((.)+?)?<br/></a></td>";
//        String reg = "<td>((\\d)+?)?</td><td>(([\u4E00-\u9FA5])+?)?</td>";
        String reg = "<td><a href='((.)+?)?\\.html'>((.)+?)?</a></td><td><a href='((.)+?)?\\.html'>((.)+?)?</a></td>";
        
        Pattern mob = Pattern.compile(reg);
        Matcher m = mob.matcher(input);
        
        while(m.find()){
            int st = m.start();
            int ed = m.end();
            String g = m.group();
            
            int gc = m.groupCount();
            
//            System.out.println(st+"~"+ed+" : "+g);
//            System.out.println(m.group(1)+"==="+m.group(3)+"==="+m.group(5)+"==="+m.group(7));// 01/440103===440103000000===01/440103===荔湾区
            rt.put(m.group(3), m.group(7));
        }
        return rt;
    }
    
    static HashMap<String,String> findJdTD1(String input){//分两类
        //<td>440113007000</td><td>市桥街道</td> || <td><a href='13/440113007.html'>440113007000</a></td><td><a href='13/440113007.html'>市桥街道</a></td>
        HashMap<String, String> rt = new HashMap<String, String>();
        
//        String reg = "<td><a href='((.)+?)?\\.html'>((.)+?)?<br/></a></td>";
        String reg = "<td>((\\d)+?)?</td><td>(([\u4E00-\u9FA5])+?)?</td>";
        
        Pattern mob = Pattern.compile(reg);
        Matcher m = mob.matcher(input);
        
        while(m.find()){
            int st = m.start();
            int ed = m.end();
            String g = m.group();
            
            int gc = m.groupCount();
            
//            System.out.println(st+"~"+ed+" : "+g);
//            System.out.println(m.group(1)+"==="+m.group(3));// 440101000000===市辖区
            rt.put(m.group(1), m.group(3));
        }
        return rt;
    }
    
    
    
    static HashMap<String,String> findQu(String input){
        HashMap<String, String> rt = new HashMap<String, String>();
        
        String reg = "<tr class='countytr'>(.)+?</tr>";//这个里面内容分两类:<td>440101000000</td><td>市辖区</td> || <td><a href='01/440103.html'>440103000000</a></td><td><a href='01/440103.html'>荔湾区</a></td>
        
        Pattern mob = Pattern.compile(reg);
        Matcher m = mob.matcher(input);
        
        while(m.find()){
            int st = m.start();
            int ed = m.end();
            String g = m.group();
            
//            System.out.println(st+"~"+ed+" : "+g);
            
            HashMap<String, String> t = findQuTD1(g);//=========================================================这种没有下级链接了
            HashMap<String, String> t2 = findQuTD2(g);
            rt.putAll(t);
            rt.putAll(t2);
        }
//        System.out.println(rt);
        return rt;
    }
    
    static HashMap<String,String> findQuTD2(String input){//分两类
        //<td>440101000000</td><td>市辖区</td> || <td><a href='01/440103.html'>440103000000</a></td><td><a href='01/440103.html'>荔湾区</a></td>
        HashMap<String, String> rt = new HashMap<String, String>();
        
//        String reg = "<td><a href='((.)+?)?\\.html'>((.)+?)?<br/></a></td>";
//        String reg = "<td>((\\d)+?)?</td><td>(([\u4E00-\u9FA5])+?)?</td>";
        String reg = "<td><a href='((.)+?)?\\.html'>((.)+?)?</a></td><td><a href='((.)+?)?\\.html'>((.)+?)?</a></td>";
        
        Pattern mob = Pattern.compile(reg);
        Matcher m = mob.matcher(input);
        
        while(m.find()){
            int st = m.start();
            int ed = m.end();
            String g = m.group();
            
            int gc = m.groupCount();
            
//            System.out.println(st+"~"+ed+" : "+g);
//            System.out.println(m.group(1)+"==="+m.group(3)+"==="+m.group(5)+"==="+m.group(7));// 01/440103===440103000000===01/440103===荔湾区
            rt.put(m.group(3), m.group(7));
        }
        return rt;
    }
    
    static HashMap<String,String> findQuTD1(String input){//分两类
        //<td>440101000000</td><td>市辖区</td> || <td><a href='01/440103.html'>440103000000</a></td><td><a href='01/440103.html'>荔湾区</a></td>
        HashMap<String, String> rt = new HashMap<String, String>();
        
//        String reg = "<td><a href='((.)+?)?\\.html'>((.)+?)?<br/></a></td>";
        String reg = "<td>((\\d)+?)?</td><td>(([\u4E00-\u9FA5])+?)?</td>";
        
        Pattern mob = Pattern.compile(reg);
        Matcher m = mob.matcher(input);
        
        while(m.find()){
            int st = m.start();
            int ed = m.end();
            String g = m.group();
            
            int gc = m.groupCount();
            
//            System.out.println(st+"~"+ed+" : "+g);
//            System.out.println(m.group(1)+"==="+m.group(3));// 440101000000===市辖区
            rt.put(m.group(1), m.group(3));
        }
        return rt;
    }
    
    
    
    
    static HashMap<String,String> findCity(String input){
        HashMap<String, String> rt = new HashMap<String, String>();
        
        String reg = "<tr class='citytr'>(.)+?</tr>";
        
        Pattern mob = Pattern.compile(reg);
        Matcher m = mob.matcher(input);
        
        while(m.find()){
            int st = m.start();
            int ed = m.end();
            String g = m.group();
            
//            System.out.println(st+"~"+ed+" : "+g);
            
            HashMap<String, String> t = findCityTD(g);
            rt.putAll(t);
        }
//        System.out.println(rt);
        return rt;
    }
    
    static HashMap<String,String> findCityTD(String input){
        //<tr class='citytr'><td><a href='44/4401.html'>440100000000</a></td><td><a href='44/4401.html'>广州市</a></td></tr>
        HashMap<String, String> rt = new HashMap<String, String>();
        
//        String reg = "<td><a href='((.)+?)?\\.html'>((.)+?)?<br/></a></td>";
        String reg = "<td><a href='((.)+?)?\\.html'>((.)+?)?</a></td><td><a href='((.)+?)?\\.html'>((.)+?)?</a></td>";
        
        Pattern mob = Pattern.compile(reg);
        Matcher m = mob.matcher(input);
        
        while(m.find()){
            int st = m.start();
            int ed = m.end();
            String g = m.group();
            
            int gc = m.groupCount();
            
//            System.out.println(st+"~"+ed+" : "+g);
//            System.out.println(m.group(1)+"==="+m.group(3)+"==="+m.group(5)+"==="+m.group(7));// 44/4451===445100000000===44/4451===潮州市
            rt.put(m.group(3), m.group(7));
        }
        return rt;
    }
    
    
    
    static String getHtml(String surl){
        StringBuilder sb = new StringBuilder();
        try {
            URL getUrl = new URL(surl);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"GBK"));
            
            
            String lines;
            while ((lines = reader.readLine()) != null) {
//                System.out.println(lines);
                sb.append(lines);
            }
            
            reader.close();
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("================"+e.getMessage());
        }
//        String surl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/";
        
        
//        System.out.println(sb);
        return String.valueOf(sb);
    }
    
    static HashMap<String,String> findProvin(String input){
        HashMap<String, String> rt = new HashMap<String, String>();
        
        String reg = "<tr class='provincetr'>(.)+?</tr>";
        
        Pattern mob = Pattern.compile(reg);
        Matcher m = mob.matcher(input);
        
        while(m.find()){
            int st = m.start();
            int ed = m.end();
            String g = m.group();
            
//            System.out.println(st+"~"+ed+" : "+g);
            
            HashMap<String, String> t = findProvinTD(g);
            rt.putAll(t);
        }
//        System.out.println(rt);
        return rt;
    }
    
    
    static HashMap<String,String> findProvinTD(String input){
        HashMap<String, String> rt = new HashMap<String, String>();
        
        String reg = "<td><a href='((.)+?)?\\.html'>((.)+?)?<br/></a></td>";
        
        Pattern mob = Pattern.compile(reg);
        Matcher m = mob.matcher(input);
        
        while(m.find()){
            int st = m.start();
            int ed = m.end();
            String g = m.group();
            
//            int gc = m.groupCount();
            
//            System.out.println(st+"~"+ed+" : "+g);
//            System.out.println(m.group(1)+"==="+m.group(3));//44===广东省
            rt.put(m.group(1), m.group(3));
        }
        return rt;
    }
    
    
    
    
    
    
    
    
    static void find(String reg,String input){
//      String reg = "((\\d+),)+";
//      reg = "(\\w+,)+";//最大匹配
//      reg = "\\d*,";//最小匹配
//      reg = "\\d+,";//一般匹配
        Pattern mob = Pattern.compile(reg);
        Matcher m = mob.matcher(input);
        
        while(m.find()){
            int st = m.start();
            int ed = m.end();
            String g = m.group();
//          String g1 = m.group(1);// java.lang.IndexOutOfBoundsException: No group 1
            
            System.out.println(st+"~"+ed+" : "+g);
        }
        
        
    }

}

