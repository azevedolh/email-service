package com.desafio.emailservice.service;

import com.desafio.emailservice.dto.CustomerResponseDTO;
import com.desafio.emailservice.exception.CustomBusinessException;

import java.util.UUID;

public interface CustomerService {

    CustomerResponseDTO getCustomerById(UUID customerId) throws CustomBusinessException;

}
