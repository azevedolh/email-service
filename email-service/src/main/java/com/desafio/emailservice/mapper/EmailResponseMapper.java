package com.desafio.emailservice.mapper;

import com.desafio.emailservice.dto.EmailResponseDTO;
import com.desafio.emailservice.model.Email;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailResponseMapper extends EntityMapper<EmailResponseDTO, Email> {
}
