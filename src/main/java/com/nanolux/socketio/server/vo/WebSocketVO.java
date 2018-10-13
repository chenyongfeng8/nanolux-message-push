package com.nanolux.socketio.server.vo;

public class WebSocketVO<T> {

	private T data;
	private String room;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

}
