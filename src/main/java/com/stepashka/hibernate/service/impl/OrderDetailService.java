package com.stepashka.hibernate.service.impl;

import com.stepashka.hibernate.entity.OrderDetailEntity;
import com.stepashka.hibernate.repository.OrderDetailRepository;
import com.stepashka.hibernate.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService implements CRUDService {
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository){
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetailEntity getObject(Long id){
        return orderDetailRepository.findById(id).get();
    }
}
