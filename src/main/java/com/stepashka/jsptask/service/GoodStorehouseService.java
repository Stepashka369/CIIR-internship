package com.stepashka.jsptask.service;

import com.stepashka.jsptask.entity.Storehouse;
import com.stepashka.jsptask.repository.GoodStorehouseDao;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class GoodStorehouseService {
    private final GoodStorehouseDao repository = new GoodStorehouseDao();
    private static final Logger logger = Logger.getLogger(GoodStorehouseService.class);

    public Integer save(Storehouse entity) {
        Integer changes = 0;
        try {
            changes = repository.save(entity);
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
        return changes;
    }
}
