package com.stepashka.jsptask.service;

import com.stepashka.jsptask.entity.Good;
import com.stepashka.jsptask.entity.Manufacturer;
import com.stepashka.jsptask.repository.GoodDao;
import com.stepashka.jsptask.repository.ManufacturerDao;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ManufacturerService {
    private final ManufacturerDao repository = new ManufacturerDao();
    private static final Logger logger = Logger.getLogger(ManufacturerService.class);

    public List<Manufacturer> findAll(){
        try {
            List<Manufacturer> manufacturers = repository.findAll();
            return manufacturers;
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }
}
