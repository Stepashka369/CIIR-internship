package com.stepashka.hibernate.mapper;

import com.stepashka.hibernate.dto.ClientDTO;
import com.stepashka.hibernate.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDTO(ClientEntity entity);
    ClientEntity toEntity(ClientDTO dto);
}
