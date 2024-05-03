package com.stepashka.hibernate.service;

import com.stepashka.hibernate.exception.NotFoundException;

import java.util.List;
import java.util.NoSuchElementException;

public interface CRUDService<T, G>{

    List<T> getAll();

    T getById(G id) throws NotFoundException;

    T saveUpdate(T entity);

    void deleteById(G id);

}
