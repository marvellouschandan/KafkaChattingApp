package com.epam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.epam.config.Producer;
import com.epam.model.ChatMessage;


// https://stackoverflow.com/questions/27047310/path-variables-in-spring-websockets-sendto-mapping
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/messaging/simp/SimpMessageHeaderAccessor.html
@Controller
public class ChatController {
	@Autowired
	Producer producer;
	
	@MessageMapping("/chat.register")
	public void register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		producer.publishToTopic(chatMessage);
	}
	
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public void sendMessage(@Payload ChatMessage chatMessage) {
		producer.publishToTopic(chatMessage);
	}
}
