package com.stepashka.hibernate.service;

import com.stepashka.hibernate.exception.NotFoundException;

import java.util.List;

public interface CRUDService<T>{

    List<T> getAll();

    T getById(Long id) throws NotFoundException;

    T saveUpdate(T entity);

    void deleteById(Long id);

}
