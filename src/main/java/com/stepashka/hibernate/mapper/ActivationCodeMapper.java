package com.stepashka.hibernate.mapper;

import com.stepashka.hibernate.dto.ActivationCodeDTO;
import com.stepashka.hibernate.entity.ActivationCodeEntity;

public interface ActivationCodeMapper {

    ActivationCodeDTO toDTO(ActivationCodeEntity entity);
    ActivationCodeEntity toEntity(ActivationCodeDTO dto);
}
