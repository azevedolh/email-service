package com.desafio.emailservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailFilterDTO {
    private UUID customerId;
    private String agency;
    private Long account;
}
