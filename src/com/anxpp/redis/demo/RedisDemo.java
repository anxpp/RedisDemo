package com.anxpp.redis.demo;
import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * Redis测试Demo
 * @author anxpp.com
 */
public class RedisDemo {
	JedisPool pool;
    Jedis jedis;
    public RedisDemo() {
    	pool = new JedisPool(new JedisPoolConfig(), "192.168.40.128");
    	jedis = pool.getResource();
//    	jedis.auth("password");
	}
    /**
     * 添加一个新值，若这个key已经存在，则替换掉之前的值
     * @param key 值的key
     * @param value 要添加的值
     * @return 添加成功时会返回“OK”，否则返回错误信息
     */
    public String setString(String key,String value){
    	return jedis.set(key, value);
    }
    /**
     * 获取值
     * @param key 要获取的值对应的key
     * @return 若key存在，返回对应的值，若不存在，返回null
     */
    public String getString(String key){
    	return jedis.get(key);
    }
    /**
     * 修改数据
     * @param key 要修改的值的key
     * @param appendStr 要添加的内容
     * @return 返回当前key对应String的长度
     * 若key不存在，就新增值，若存在，就添加到已存在的String的后面
     */
    public long appendString(String key,String appendStr){
    	jedis.del(key);
    	return jedis.append(key, appendStr);
    }
    /**
     * 删除String
     * @param key 要删除的值的key
     * @return 返回删除的个数
     * 若key不存在，就返回0，表示一个都没有删除
     */
    public long delString(String key){
    	return jedis.del(key);
    }
    public String setMap(String key,Map<String, String> map){
    	return jedis.hmset(key,map);
    }
    public void getMap(String key,String... fields){
    	jedis.hmget(key, fields);
    }
    public void close(){
    	jedis.close();
    }
    public static void main(String args[]){
    	RedisDemo redisDemo = new RedisDemo();
    	System.out.println(redisDemo.getString("name"));
    	System.out.println(redisDemo.setString("name","iamanxpp"));
    	System.out.println(redisDemo.getString("name"));
    	System.out.println(redisDemo.appendString("name", "_append"));
    	System.out.println(redisDemo.appendString("123", "_append"));
    	System.out.println(redisDemo.getString("name"));
    	System.out.println(redisDemo.getString("123"));
    	System.out.println(redisDemo.delString("123"));
    	System.out.println(redisDemo.delString("234"));
    	Map<String,String> user=new HashMap<String,String>();
        user.put("username","anxpp");
        user.put("password","password");
    	System.out.println(redisDemo.setMap("map", user));
    	redisDemo.close();
    }
}
