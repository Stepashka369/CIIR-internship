package com.stepashka.hibernate.mapper;

import com.stepashka.hibernate.dto.DeliveryDTO;
import com.stepashka.hibernate.entity.DeliveryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    DeliveryDTO toDTO(DeliveryEntity entity);
    DeliveryEntity toEntity(DeliveryDTO dto);
}
