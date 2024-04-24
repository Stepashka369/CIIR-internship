package com.stepashka.hibernate.mapper;

import com.stepashka.hibernate.dto.StorehouseDTO;
import com.stepashka.hibernate.entity.StorehouseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorehouseMapper {

    StorehouseDTO toDTO(StorehouseEntity entity);
    StorehouseEntity toEntity(StorehouseDTO dto);
}
