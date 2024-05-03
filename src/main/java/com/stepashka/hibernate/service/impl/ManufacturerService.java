package com.stepashka.hibernate.service.impl;

import com.stepashka.hibernate.entity.ManufacturerEntity;
import com.stepashka.hibernate.exception.NotFoundException;
import com.stepashka.hibernate.repository.ManufacturerRepository;
import com.stepashka.hibernate.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ManufacturerService implements CRUDService<ManufacturerEntity, Long> {

    private ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository){
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<ManufacturerEntity> getAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public ManufacturerEntity getById(Long id) throws NotFoundException {
        return manufacturerRepository.findById(id).orElseThrow(() -> new NotFoundException("Manufacturer not found"));
    }

    @Override
    public ManufacturerEntity saveUpdate(ManufacturerEntity entity) {
        return manufacturerRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        manufacturerRepository.deleteById(id);
    }
}
