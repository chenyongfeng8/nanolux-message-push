package com.nanolux.socketio.server.vo;

import java.io.Serializable;

public class WebSocketVO<T> implements Serializable{

	private T data;
	private String room;
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
