package com.desafio.emailservice.mapper;

import com.desafio.emailservice.dto.EmailResponseDTO;
import com.desafio.emailservice.model.Email;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-06T01:17:57-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class EmailResponseMapperImpl implements EmailResponseMapper {

    @Override
    public Email toEntity(EmailResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Email.EmailBuilder email = Email.builder();

        email.id( dto.getId() );
        email.customerId( dto.getCustomerId() );
        email.emailFrom( dto.getEmailFrom() );
        email.emailTo( dto.getEmailTo() );
        email.subject( dto.getSubject() );
        email.content( dto.getContent() );
        email.status( dto.getStatus() );
        email.errorDetails( dto.getErrorDetails() );
        email.createdAt( dto.getCreatedAt() );
        email.updatedAt( dto.getUpdatedAt() );

        return email.build();
    }

    @Override
    public EmailResponseDTO toDto(Email entity) {
        if ( entity == null ) {
            return null;
        }

        EmailResponseDTO.EmailResponseDTOBuilder emailResponseDTO = EmailResponseDTO.builder();

        emailResponseDTO.id( entity.getId() );
        emailResponseDTO.emailFrom( entity.getEmailFrom() );
        emailResponseDTO.emailTo( entity.getEmailTo() );
        emailResponseDTO.subject( entity.getSubject() );
        emailResponseDTO.content( entity.getContent() );
        emailResponseDTO.status( entity.getStatus() );
        emailResponseDTO.errorDetails( entity.getErrorDetails() );
        emailResponseDTO.createdAt( entity.getCreatedAt() );
        emailResponseDTO.updatedAt( entity.getUpdatedAt() );

        return emailResponseDTO.build();
    }

    @Override
    public List<Email> toEntity(List<EmailResponseDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Email> list = new ArrayList<Email>( dtoList.size() );
        for ( EmailResponseDTO emailResponseDTO : dtoList ) {
            list.add( toEntity( emailResponseDTO ) );
        }

        return list;
    }

    @Override
    public List<EmailResponseDTO> toDto(List<Email> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<EmailResponseDTO> list = new ArrayList<EmailResponseDTO>( dtoList.size() );
        for ( Email email : dtoList ) {
            list.add( toDto( email ) );
        }

        return list;
    }

    @Override
    public Set<EmailResponseDTO> toDto(Set<Email> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<EmailResponseDTO> set = new LinkedHashSet<EmailResponseDTO>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( Email email : entityList ) {
            set.add( toDto( email ) );
        }

        return set;
    }
}
