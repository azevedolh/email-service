package com.desafio.emailservice.mapper;

import com.desafio.emailservice.dto.PageableResponseDTO;
import org.springframework.data.domain.Page;

public interface PageableMapper {
    PageableResponseDTO toDto(Page<?> page);
}
