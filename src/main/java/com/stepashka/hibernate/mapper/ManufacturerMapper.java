package com.stepashka.hibernate.mapper;

import com.stepashka.hibernate.dto.ManufacturerDTO;
import com.stepashka.hibernate.entity.ManufacturerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManufacturerMapper {
    ManufacturerDTO toDTO(ManufacturerEntity entity);
    ManufacturerEntity toEntity(ManufacturerDTO dto);
}
