package com.desafio.emailservice.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<T> {

    private PageableResponseDTO _pageable;
    private List<T> _content;
}
