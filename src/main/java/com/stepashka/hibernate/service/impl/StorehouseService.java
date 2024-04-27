package com.stepashka.hibernate.service.impl;

import com.stepashka.hibernate.entity.GoodEntity;
import com.stepashka.hibernate.entity.StorehouseEntity;
import com.stepashka.hibernate.exception.NotFoundException;
import com.stepashka.hibernate.repository.StorehouseRepository;
import com.stepashka.hibernate.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StorehouseService implements CRUDService<StorehouseEntity> {

    private StorehouseRepository storehouseRepository;

    @Autowired
    public StorehouseService(StorehouseRepository storehouseRepository){
        this.storehouseRepository = storehouseRepository;
    }

    @Override
    public List<StorehouseEntity> getAll() {
        return storehouseRepository.findAll();
    }

    @Override
    public StorehouseEntity getById(Long id) throws NotFoundException {
        return storehouseRepository.findById(id).orElseThrow(() -> new NotFoundException("Storehouse not found"));
    }

    @Override
    public StorehouseEntity saveUpdate(StorehouseEntity entity) {
        return storehouseRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        storehouseRepository.deleteById(id);
    }
}
