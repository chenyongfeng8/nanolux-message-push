package com.nanolux.socketio.server;

import java.util.Timer;
import java.util.TimerTask;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.corundumstudio.socketio.namespace.Namespace;
import com.nanolux.socketio.server.vo.WebSocketVO;

public class WebSocketLauncher {

	private static String hostname = "localhost";
	private static int port = 9092;

	public static void main(String[] args) throws InterruptedException {

		Configuration config = new Configuration();
		config.setHostname(hostname);
		config.setPort(port);
		final SocketIOServer server = new SocketIOServer(config);

		server.addConnectListener(new ConnectListener() {
			public void onConnect(SocketIOClient client) {
				String room = client.getHandshakeData().getSingleUrlParam("room");
				client.joinRoom(room);
				System.out.println(room);
			}
		});

		server.addEventListener("chatevent", WebSocketVO.class, new DataListener<WebSocketVO>() {
			public void onData(SocketIOClient client, WebSocketVO data, AckRequest ackRequest) {
				// broadcast messages to all clients
				server.getBroadcastOperations().sendEvent("chatevent", data);
//                client.sendEvent("chatevent", data);
			}
		});

		server.addDisconnectListener(new DisconnectListener() {
			public void onDisconnect(SocketIOClient client) {
				String room = client.getHandshakeData().getSingleUrlParam("room");
				client.leaveRoom(room);
			}
		});

		server.start();

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				SocketIONamespace namespace = server.getNamespace(Namespace.DEFAULT_NAME);
				BroadcastOperations roomOperations = namespace.getRoomOperations("room");
				roomOperations.sendEvent("chatevent", "currentTimeMillis==" + System.currentTimeMillis());
			}
		}, 5000, 2000);
	}

}
