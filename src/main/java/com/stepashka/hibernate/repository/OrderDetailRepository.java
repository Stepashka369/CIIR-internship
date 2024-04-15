package com.stepashka.hibernate.repository;

import com.stepashka.hibernate.entity.OrderDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetailEntity, Long> {
}
