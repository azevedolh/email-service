package com.desafio.emailservice.service.impl;

import com.desafio.emailservice.dto.ApiErrorResponseDTO;
import com.desafio.emailservice.dto.CustomerResponseDTO;
import com.desafio.emailservice.exception.CustomBusinessException;
import com.desafio.emailservice.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Log4j2
@Service
public class CustomerServiceImpl implements CustomerService {
    private final RestTemplate restTemplate;

    @Value("${customer.service.url}")
    private String CUSTOMER_SERVICE_URL;

    @Autowired
    public CustomerServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CustomerResponseDTO getCustomerById(UUID customerId) throws CustomBusinessException {

        ResponseEntity<CustomerResponseDTO> response = null;

        try {
            response = restTemplate.getForEntity(
                    CUSTOMER_SERVICE_URL + "/" + customerId,
                    CustomerResponseDTO.class
            );

            return response.getBody();
        } catch (HttpClientErrorException e) {
            ApiErrorResponseDTO errorResponse = e.getResponseBodyAs(ApiErrorResponseDTO.class);

            throw new CustomBusinessException(
                errorResponse != null ? errorResponse.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR,
                errorResponse != null ? errorResponse.getMessage() : "Erro na comunicação com serviço de Clientes",
                errorResponse != null ? errorResponse.getDetails() : ""
            );
        }
    }
}
