package com.stepashka.hibernate.mapper;

import com.stepashka.hibernate.dto.GoodImageDTO;
import com.stepashka.hibernate.entity.GoodImageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoodImageMapper {
    GoodImageDTO toDTO(GoodImageEntity entity);

    GoodImageEntity toEntity(GoodImageDTO dto);
}
