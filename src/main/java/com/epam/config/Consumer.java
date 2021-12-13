package com.epam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.epam.model.ChatMessage;

@Service
public class Consumer {
	@Value("${socket.topic}")
	private String SOCKET_TOPIC;
	
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	
	@KafkaListener(topics="${kafka.topic}", groupId="${group.id}")
	public void consumeFromTopic(@Payload ChatMessage chatMessage) {
		System.out.println("Consumed message : " + chatMessage);
		simpMessagingTemplate.convertAndSend("/topic/" + SOCKET_TOPIC, chatMessage);
	}
}
