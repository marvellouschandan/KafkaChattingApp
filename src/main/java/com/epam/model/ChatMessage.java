package com.epam.model;

import lombok.Data;

@Data
public class ChatMessage {
	private String content;
	private String sender;
	private MessageType type;
	private String group;

	public enum MessageType {
		CHAT, LEAVE, JOIN
	}
}
