package com;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
 
@Component
public class MessageEventHandler 
{
	private final SocketIOServer server;
	
	@Autowired
    public MessageEventHandler(SocketIOServer server) 
	{
        this.server = server;
    }
	//添加connect事件，当客户端发起连接时调用，本文中将clientid与sessionid存入数据库
	//方便后面发送消息时查找到对应的目标client,
	@OnConnect
    public void onConnect(SocketIOClient client)
    {
		String room = client.getHandshakeData().getSingleUrlParam("room");
		client.joinRoom(room);
		System.out.println(room);
    }
	
	//添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
	@OnDisconnect
	public void onDisconnect(SocketIOClient client)
	{
		String room = client.getHandshakeData().getSingleUrlParam("room");
		client.leaveRoom(room);
	}
	
	//消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
	@OnEvent(value = "chatevent_client")
    public void onEvent(SocketIOClient client, AckRequest request, String data) 
	{
		client.sendEvent("chatevent", data);
    }
}
