package com.desafio.emailservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    @NotBlank
    private UUID customerId;

    @NotBlank
    private String agency;

    @NotBlank
    private Long account;

    @NotBlank
    private String subject;

    @NotBlank
    private String message;
}
