package pl.edu.agh.lab.toik.comm.v1.impl;

import pl.edu.agh.lab.toik.comm.v1.MessageType;

public class Message {

	private MessageType type;

	private Object value;

	public Message(MessageType type, String value) {
		this.type = type;
		this.value = value;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
