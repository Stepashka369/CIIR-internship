package com.stepashka.hibernate.mapper;

import com.stepashka.hibernate.dto.PaymentDTO;
import com.stepashka.hibernate.entity.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDTO toDTO(PaymentEntity entity);
    PaymentEntity toEntity(PaymentDTO dto);
}
