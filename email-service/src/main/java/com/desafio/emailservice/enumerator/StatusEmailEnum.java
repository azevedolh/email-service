package com.desafio.emailservice.enumerator;

public enum StatusEmailEnum {
    SENT(1l, "Enviado"),
    ERROR(2l, "Erro");

    private final Long code;
    private final String description;

    StatusEmailEnum(Long code, String description) {
        this.code = code;
        this.description = description;
    }
}
