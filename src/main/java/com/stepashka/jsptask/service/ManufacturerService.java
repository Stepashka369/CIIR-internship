package com.stepashka.jsptask.service;

import com.stepashka.jsptask.entity.Manufacturer;
import com.stepashka.jsptask.repository.ManufacturerDao;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerService {
    private final ManufacturerDao repository = new ManufacturerDao();
    private static final Logger logger = Logger.getLogger(ManufacturerService.class);

    public List<Manufacturer> findAll(){
        try {
            return repository.findAll();
            return manufacturers;
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
        return new ArrayList<>();
    }
}
