package com.desafio.emailservice.mapper;

import com.desafio.emailservice.dto.EmailResponseDTO;
import com.desafio.emailservice.dto.NotificationDTO;
import com.desafio.emailservice.model.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends EntityMapper<NotificationDTO, Email> {

    @Mapping(target = "content", source = "message")
    public Email toEntity(NotificationDTO dto);
}
