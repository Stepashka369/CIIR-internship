package com.stepashka.hibernate.repository;

import com.stepashka.hibernate.entity.ManufacturerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends CrudRepository<ManufacturerEntity, Long> {

    List<ManufacturerEntity> findAll();
}
