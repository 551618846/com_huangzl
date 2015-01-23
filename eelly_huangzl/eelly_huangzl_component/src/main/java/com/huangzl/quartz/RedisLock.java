/** 
* @组件名：cas-server-webapp
* @包名：com.eelly.cas.redis
* @文件名：RedisLock.java
* @创建时间： 2014年12月18日 下午4:17:01
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.quartz;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

/**
 * @类名：RedisLock
 * @描述: 来自网络.一个redis实现的"跨jvm"的lock.锁实现参考http://redis.io/commands/set
 * <p>原理:(lock方法使用redis设置加锁标识,当设置成功后,其他线程调用lock方法设置不成功会休眠,直到获取锁超时或其他线程调用unlock释放锁或锁过期)(redis单线程,所以是线程安全的锁) 
 * <p>一般一个key只创建一个RedisLock对象,在该对象上lock,unlock;同key不同RedisLock对象不能互相unlock,但是能互相lock互斥
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年12月18日 下午4:17:01
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class RedisLock {

    /** 加锁标志 */
    public static final String LOCKED = "TRUE";
    
    /** 毫秒与毫微秒的换算单位 1毫秒 = 1000000毫微秒 */
    public static final long MILLI_NANO_CONVERSION = 1000 * 1000L;
    
    /** 默认获取锁-超时时间（毫秒） */
    public static final long DEFAULT_TIME_OUT = 1000;
    
    public static final Random RANDOM = new Random();
    
    /** 锁的过期时间(加锁后未释放的锁会过期)（秒），过期删除 */
    public static final int EXPIRE = 3 * 60;

    private ShardedJedis jedis;
    private String key;
    // 锁状态标志
    private boolean locked = false;
    
    private String token;//标识,只有加锁才能解锁
    
    private static final String OK = "OK";
    
    private int currentExpire = 0;

    /**
     * This creates a RedisLock
     * @param key key
     * @param shardedJedisPool 数据源
     */
    public RedisLock(String key) {
        this.key = key + "_lock";
        List<JedisShardInfo> shards = Arrays.asList(
                new JedisShardInfo("localhost",6379));

        this.jedis = new ShardedJedis(shards);
    }
    
    
    public void freshExpire(){
        try {
            this.jedis.expire(this.key, currentExpire);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用 
     * @param expire 过期时间,秒
     * @return true-成功,false-失败(或Exception[比如redis失败])
     */
    public boolean lockNoTimeout(int expire) {
        this.currentExpire = expire;
        long nano = System.nanoTime();
        try {
            while (true) {
                String rt = this.jedis.set(this.key, String.valueOf(nano), "NX", "EX", expire);//线程安全//锁实现参考http://redis.io/commands/set
                if (OK.equals(rt)) {//注意key超时后setnx可以成功//this.jedis.set(this.key, LOCKED, "NX", "EX", expire)//this.jedis.setnx(this.key, LOCKED) == 1
                    //this.jedis.expire(this.key, expire);
                    token = String.valueOf(nano);//加锁成功后设置标识(如果线程crash,只能等待锁超时)
                    this.locked = true;
                    return this.locked;
                }
                // 短暂休眠，避免出现活锁
                Thread.sleep(1000 * 5);
            }
        } catch (Exception e) {
            this.locked = false;
            this.jedis.del(this.key);
            throw new RuntimeException("Locking error", e);
        }
    }

    /**
     * 加锁,默认3*60锁过期
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用 
     * @param timeout 获取锁-超时时间,毫秒
     * @return true-成功,false-失败(获取锁等待超时,或Exception[比如redis失败])
     */
    public boolean lock(long timeout) {
        return lock(timeout, EXPIRE);
    }

    /**
     * 加锁
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用
     * @param timeout 获取锁-超时时间,毫秒
     * @param expire 锁的过期时间（秒），过期删除
     * @return true-成功,false-失败(获取锁等待超时,或Exception[比如redis失败])
     */
    public boolean lock(long timeout, int expire) {
        this.currentExpire = expire;
        long nano = System.nanoTime();
        timeout *= MILLI_NANO_CONVERSION;
        try {
            while ((System.nanoTime() - nano) < timeout) {
                String rt = this.jedis.set(this.key, String.valueOf(nano), "NX", "EX", expire);//线程安全//锁实现参考http://redis.io/commands/set
                if (OK.equals(rt)) {//注意key超时后setnx可以成功//this.jedis.set(this.key, LOCKED, "NX", "EX", expire)//this.jedis.setnx(this.key, LOCKED) == 1
                    //this.jedis.expire(this.key, expire);
                    token = String.valueOf(nano);//加锁成功后设置标识(如果线程crash,只能等待锁超时)
                    this.locked = true;
                    return this.locked;
                }
                // 短暂休眠，避免出现活锁
                Thread.sleep(3, RANDOM.nextInt(500));
            }
        } catch (Exception e) {
            this.locked = false;
            this.jedis.del(this.key);
            throw new RuntimeException("Locking error", e);
        }
        return false;
    }

    /**
     * 加锁,默认1000毫秒获取锁超时,3*60锁过期
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用
     * @return true-成功,false-失败(获取锁等待超时,或Exception[比如redis失败])
     */
    public boolean lock() {
        return lock(DEFAULT_TIME_OUT);
    }

    /**
     * 解锁
     * 无论是否加锁成功，都需要调用unlock
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用
     */
    public void unlock() {
        try {
            String get = this.jedis.get(this.key);
            if(token.equals(get)){
                this.jedis.del(this.key);//token避免持有过期锁的客户端误删现有锁
            }
            /*if (this.locked) {
                this.jedis.del(this.key);//持有过期锁的客户端误删现有锁(//可能误删其他线程获取的锁)
            }*/
        } finally {
            
        }
    }
}


