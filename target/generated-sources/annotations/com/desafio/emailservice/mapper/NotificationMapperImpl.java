package com.desafio.emailservice.mapper;

import com.desafio.emailservice.dto.NotificationDTO;
import com.desafio.emailservice.model.Email;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-06T15:33:03-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationDTO toDto(Email entity) {
        if ( entity == null ) {
            return null;
        }

        NotificationDTO.NotificationDTOBuilder notificationDTO = NotificationDTO.builder();

        notificationDTO.customerId( entity.getCustomerId() );
        notificationDTO.subject( entity.getSubject() );

        return notificationDTO.build();
    }

    @Override
    public List<Email> toEntity(List<NotificationDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Email> list = new ArrayList<Email>( dtoList.size() );
        for ( NotificationDTO notificationDTO : dtoList ) {
            list.add( toEntity( notificationDTO ) );
        }

        return list;
    }

    @Override
    public List<NotificationDTO> toDto(List<Email> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NotificationDTO> list = new ArrayList<NotificationDTO>( dtoList.size() );
        for ( Email email : dtoList ) {
            list.add( toDto( email ) );
        }

        return list;
    }

    @Override
    public Set<NotificationDTO> toDto(Set<Email> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<NotificationDTO> set = new LinkedHashSet<NotificationDTO>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( Email email : entityList ) {
            set.add( toDto( email ) );
        }

        return set;
    }

    @Override
    public Email toEntity(NotificationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Email.EmailBuilder email = Email.builder();

        email.content( dto.getMessage() );
        email.customerId( dto.getCustomerId() );
        email.subject( dto.getSubject() );

        return email.build();
    }
}
