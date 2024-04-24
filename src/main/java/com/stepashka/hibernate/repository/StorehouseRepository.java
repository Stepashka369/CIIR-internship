package com.stepashka.hibernate.repository;

import com.stepashka.hibernate.entity.StorehouseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StorehouseRepository extends CrudRepository<StorehouseEntity, Long> {

    List<StorehouseEntity> findAll();
}
