package com.stepashka.jsptask.service;

import com.stepashka.jsptask.entity.Good;
import com.stepashka.jsptask.entity.Manufacturer;
import com.stepashka.jsptask.entity.Storehouse;
import com.stepashka.jsptask.repository.ManufacturerDao;
import com.stepashka.jsptask.repository.StorehouseDao;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StorehouseService {
    private final StorehouseDao repository = new StorehouseDao();
    private static final Logger logger = Logger.getLogger(StorehouseService.class);
    public List<Storehouse> findAll(){
        try {
            return repository.findAll();
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
        return new ArrayList<>();
    }

    public Storehouse findById(Integer id) {
        try {
            return repository.findById(id);
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
        return new Storehouse();
    }

    public Integer save(Storehouse entity){
        Integer changes = 0;
        try {
            changes = repository.save(entity);
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
        return changes;
    }

    public Integer delete(Integer id) {
        Integer changes = 0;
        try {
            changes = repository.delete(id);
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
        return changes;
    }
}
