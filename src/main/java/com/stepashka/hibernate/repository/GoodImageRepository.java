package com.stepashka.hibernate.repository;

import com.stepashka.hibernate.entity.GoodImageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GoodImageRepository extends CrudRepository<GoodImageEntity, Long> {
    List<GoodImageEntity> findAllByGoodId(Long goodId);
}
