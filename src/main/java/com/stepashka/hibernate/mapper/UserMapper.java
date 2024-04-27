package com.stepashka.hibernate.mapper;

import com.stepashka.hibernate.dto.UserDTO;
import com.stepashka.hibernate.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(UserEntity entity);
    UserEntity toEntity(UserDTO dto);
}
