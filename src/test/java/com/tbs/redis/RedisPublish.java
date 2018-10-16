package com.tbs.redis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.nanolux.socketio.server.vo.WebSocketVO;

import redis.clients.jedis.Jedis;

public class RedisPublish {

	public static void main(String[] args) throws IOException {
		System.out.println("发布者 ");
		Jedis jRedis = new Jedis("localhost");
		jRedis.publish("JRedisChat1", "my name is cyf");
//          jRedis.publish("JRedisChat1","Hello chenLong!");  
		
		
//		WebSocketVO o = new WebSocketVO();// 一个实体类..
//		ByteArrayOutputStream byt = new ByteArrayOutputStream();
//		ObjectOutputStream obj = new ObjectOutputStream(byt);
//
//		obj.writeObject(o);
//
//		byte[] bytes = byt.toByteArray();
//		// 可写入byte和字符串.
//		jRedis.publish("JRedisChat1".getBytes(), bytes);
	}

}
