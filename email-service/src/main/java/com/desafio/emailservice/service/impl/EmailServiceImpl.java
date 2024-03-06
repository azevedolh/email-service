package com.desafio.emailservice.service.impl;

import com.desafio.emailservice.dto.*;
import com.desafio.emailservice.enumerator.StatusEmailEnum;
import com.desafio.emailservice.exception.CustomBusinessException;
import com.desafio.emailservice.mapper.EmailResponseMapper;
import com.desafio.emailservice.mapper.NotificationMapper;
import com.desafio.emailservice.mapper.PageableMapper;
import com.desafio.emailservice.model.Email;
import com.desafio.emailservice.repository.EmailRepository;
import com.desafio.emailservice.service.CustomerService;
import com.desafio.emailservice.service.EmailService;
import com.desafio.emailservice.specification.EmailSpecifications;
import com.desafio.emailservice.util.PaginationUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.desafio.emailservice.util.ConstantUtil.SORT_BY_CREATED_AT;


@Log4j2
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${email.from}")
    private String EMAIL_FROM;
    private EmailRepository emailRepository;
    private PageableMapper pageableMapper;
    private EmailResponseMapper emailResponseMapper;
    private JavaMailSender javaMailSender;
    private NotificationMapper notificationMapper;
    private CustomerService customerService;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository,
                            PageableMapper pageableMapper,
                            EmailResponseMapper emailResponseMapper,
                            JavaMailSender javaMailSender,
                            NotificationMapper notificationMapper,
                            CustomerService customerService) {
        this.emailRepository = emailRepository;
        this.pageableMapper = pageableMapper;
        this.emailResponseMapper = emailResponseMapper;
        this.javaMailSender = javaMailSender;
        this.notificationMapper = notificationMapper;
        this.customerService = customerService;
    }

    @Override
    public PageResponseDTO getEmails(EmailFilterDTO filter, Integer page, Integer size, String sort) {

        Sort sortProperties = PaginationUtil.getSort(sort, Sort.Direction.DESC, SORT_BY_CREATED_AT);

        PageRequest pageRequest = PageRequest.of(page - 1, size, sortProperties);
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        Page<Email> emailPage = emailRepository.findAll(EmailSpecifications.generateDinamic(filter), pageRequest);

        if (emailPage != null) {
            pageResponseDTO.set_pageable(pageableMapper.toDto(emailPage));
            pageResponseDTO.set_content(emailResponseMapper.toDto(emailPage.getContent()));
        }

        return pageResponseDTO;
    }

    @Override
    public EmailResponseDTO send(NotificationDTO notificationDTO) {

        Email emailToReturn;
        Email email = notificationMapper.toEntity(notificationDTO);
        email.setEmailFrom(EMAIL_FROM);

        CustomerResponseDTO customer = null;

        try {
            customer = customerService.getCustomerById(notificationDTO.getCustomerId());
        } catch (CustomBusinessException e) {
            email.setStatus(StatusEmailEnum.ERROR);
            email.setErrorDetails(e.getMessage());
            emailToReturn = emailRepository.saveAndFlush(email);
            return emailResponseMapper.toDto(emailToReturn);
        }

        email.setEmailTo(customer.getEmail());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(EMAIL_FROM);
            message.setTo(customer.getEmail());
            message.setSubject(notificationDTO.getSubject());
            message.setText(notificationDTO.getMessage());
            javaMailSender.send(message);
            email.setStatus(StatusEmailEnum.SENT);
        } catch (MailException e) {
            email.setStatus(StatusEmailEnum.ERROR);
            email.setErrorDetails(e.getMessage());
        } finally {
            emailToReturn = emailRepository.saveAndFlush(email);
        }

        return emailResponseMapper.toDto(emailToReturn);
    }
}
