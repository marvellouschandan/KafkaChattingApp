package com.epam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.epam.model.ChatMessage;

@Service
public class Producer {
	@Value("${kafka.topic}")
	private String KAFKA_TOPIC;
	
	@Autowired
	private KafkaTemplate<String, ChatMessage> template;
	
	public void publishToTopic(ChatMessage chatMessage) {
		System.out.println("Publishing message to " + KAFKA_TOPIC + " " + chatMessage);
		template.send(KAFKA_TOPIC, chatMessage);
	}
}
