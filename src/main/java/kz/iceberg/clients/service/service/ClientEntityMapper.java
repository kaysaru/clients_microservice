package kz.iceberg.clients.service.service;

import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.entity.dto.ClientEntityDto;
import kz.iceberg.clients.service.entity.ClientMoreEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientEntityMapper {
    ClientEntity toEntity(ClientEntityDto clientEntityDto);

    @AfterMapping
    default void linkAddresses(@MappingTarget ClientEntity clientEntity) {
        clientEntity.getAddresses().forEach(address -> address.setClient(clientEntity));
    }

    @AfterMapping
    default void linkEmails(@MappingTarget ClientEntity clientEntity) {
        clientEntity.getEmails().forEach(email -> email.setClient(clientEntity));
    }

    @AfterMapping
    default void linkMore(@MappingTarget ClientEntity clientEntity) {
        ClientMoreEntity more = clientEntity.getMore();
        if (more != null) {
            more.setClient(clientEntity);
        }
    }

    @AfterMapping
    default void linkPhones(@MappingTarget ClientEntity clientEntity) {
        clientEntity.getPhones().forEach(phone -> phone.setClient(clientEntity));
    }

    ClientEntityDto toDto(ClientEntity clientEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ClientEntity partialUpdate(ClientEntityDto clientEntityDto, @MappingTarget ClientEntity clientEntity);
}