package com.wx.test;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import com.wx.utils.RedisPoolUtil;
import redis.clients.jedis.Jedis;
 
public class TestPool {
	public static void main(String[] args) {
		//初始化连接池
		RedisPoolUtil.initialPool();
		//启动1000个线程
		for (int i = 0; i < 1000; i++) {            
	        ClientThread t = new ClientThread(i);  
	        t.start();  
	    }
	}  
}
//线程类
class ClientThread extends Thread {  
    int i = 0;  
    public ClientThread(int i) {  
        this.i = i;  
    }  
    public void run() {  
    	Jedis jedis=RedisPoolUtil.getConn();
        Date date = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");  
        String time = sdf.format(date);  
        jedis.set("key"+i, time); 
        try {
        	//每次睡眠一个随机时间
			Thread.sleep((int)(Math.random()*5000));
			String foo = jedis.get("key"+i);        
	        System.out.println("【输出>>>>】key:" + foo + " 第:"+i+"个线程");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			RedisPoolUtil.closeConn();
		}
    }  
}  
