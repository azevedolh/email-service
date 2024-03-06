package com.desafio.emailservice.service;


import com.desafio.emailservice.dto.EmailFilterDTO;
import com.desafio.emailservice.dto.EmailResponseDTO;
import com.desafio.emailservice.dto.NotificationDTO;
import com.desafio.emailservice.dto.PageResponseDTO;

public interface EmailService {

    PageResponseDTO getEmails(EmailFilterDTO filter, Integer page, Integer size, String sort);

    EmailResponseDTO send(NotificationDTO notificationDTO);

}
