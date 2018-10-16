package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisReceiver {

	@Autowired
	private MessageEventHandler messageEventHandler;

	public void receiveMessage(Object message) {
		// 这里是收到通道的消息之后执行的方法
		System.out.println("message===" + message);
		messageEventHandler.sendMessage(message);
	}
}