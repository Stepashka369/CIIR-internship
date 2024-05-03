package com.stepashka.hibernate.repository;

import com.stepashka.hibernate.entity.GoodEntity;
import com.stepashka.hibernate.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    List<UserEntity> findAll();

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String Email);
}
