package com.desafio.emailservice.dto;

import com.desafio.emailservice.enumerator.StatusEmailEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailResponseDTO {

    private UUID id;

    private UUID customerId;

    private String emailFrom;

    private String emailTo;

    private String subject;

    private String content;

    private StatusEmailEnum status;

    private String errorDetails;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
