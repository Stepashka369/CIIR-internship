package com.stepashka.hibernate.repository;

import com.stepashka.hibernate.dto.GoodDTO;
import com.stepashka.hibernate.entity.GoodEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends CrudRepository<GoodEntity, Long> {
    List<GoodEntity> findAll();
}
