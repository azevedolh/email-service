package com.desafio.emailservice.controller;

import com.desafio.emailservice.dto.EmailFilterDTO;
import com.desafio.emailservice.dto.PageResponseDTO;
import com.desafio.emailservice.service.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping()
    public ResponseEntity<PageResponseDTO> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "_sort", required = false) String sort,
            @RequestParam(value = "customerId", required = false) String customerId,
            @RequestParam(value = "agency", required = false) String agency,
            @RequestParam(value = "account", required = false) Long account) {

        EmailFilterDTO filter = EmailFilterDTO.builder()
                .agency(agency)
                .account(account)
                .customerId(customerId != null ? UUID.fromString(customerId): null)
                .build();


        return new ResponseEntity<PageResponseDTO>(emailService.getEmails(
                filter,
                page,
                size,
                sort),
                HttpStatus.OK);
    }
}
