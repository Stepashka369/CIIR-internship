package com.stepashka.hibernate.mapper;

import com.stepashka.hibernate.dto.GoodDTO;
import com.stepashka.hibernate.dto.ManufacturerDTO;
import com.stepashka.hibernate.entity.GoodEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ManufacturerDTO.class)
public interface GoodMapper {
    GoodDTO toDTO(GoodEntity entity);
    GoodEntity toEntity(GoodDTO dto);
}
