package com.anxpp.dedis.demo;
import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * Redis����Demo
 * @author anxpp.com
 */
public class RedisDemo {
	JedisPool pool;
    Jedis jedis;
    public RedisDemo() {
    	pool = new JedisPool(new JedisPoolConfig(), "192.168.40.130");
    	jedis = pool.getResource();
//    	jedis.auth("password");
	}
    /**
     * ���һ����ֵ�������key�Ѿ����ڣ����滻��֮ǰ��ֵ
     * @param key ֵ��key
     * @param value Ҫ��ӵ�ֵ
     * @return ��ӳɹ�ʱ�᷵�ء�OK�������򷵻ش�����Ϣ
     */
    public String setString(String key,String value){
    	return jedis.set(key, value);
    }
    /**
     * ��ȡֵ
     * @param key Ҫ��ȡ��ֵ��Ӧ��key
     * @return ��key���ڣ����ض�Ӧ��ֵ���������ڣ�����null
     */
    public String getString(String key){
    	return jedis.get(key);
    }
    /**
     * �޸�����
     * @param key Ҫ�޸ĵ�ֵ��key
     * @param appendStr Ҫ��ӵ�����
     * @return ���ص�ǰkey��ӦString�ĳ���
     * ��key�����ڣ�������ֵ�������ڣ�����ӵ��Ѵ��ڵ�String�ĺ���
     */
    public long appendString(String key,String appendStr){
    	jedis.del(key);
    	return jedis.append(key, appendStr);
    }
    /**
     * ɾ��String
     * @param key Ҫɾ����ֵ��key
     * @return ����ɾ���ĸ���
     * ��key�����ڣ��ͷ���0����ʾһ����û��ɾ��
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
