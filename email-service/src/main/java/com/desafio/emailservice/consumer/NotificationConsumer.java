package com.desafio.emailservice.consumer;

import com.desafio.emailservice.dto.NotificationDTO;
import com.desafio.emailservice.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EmailService emailService;

    @KafkaListener(topics = "hvzlydbz-default")
    public void receiveMessage(String message){

        NotificationDTO notificationDTO = null;

        try {
            notificationDTO = objectMapper.readValue(message, NotificationDTO.class);
        } catch (JsonProcessingException e) {
            log.error("Erro na conversão da notificação");
        }

        emailService.send(notificationDTO);
    }
}
