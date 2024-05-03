package com.stepashka.hibernate.repository;

import com.stepashka.hibernate.entity.ActivationCodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivationCodeRepository extends CrudRepository<ActivationCodeEntity, String> {
    List<ActivationCodeEntity> findAll();
}