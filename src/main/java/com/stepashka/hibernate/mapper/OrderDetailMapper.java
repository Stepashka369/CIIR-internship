package com.stepashka.hibernate.mapper;

import com.stepashka.hibernate.dto.OrderDetailDTO;
import com.stepashka.hibernate.entity.OrderDetailEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DeliveryMapper.class, PaymentMapper.class, GoodMapper.class})
public interface OrderDetailMapper {
    OrderDetailDTO toDTO(OrderDetailEntity entity);
    OrderDetailEntity toEntity(OrderDetailDTO dto);
}
