package com.stepashka.jsptask.service;

import com.stepashka.jsptask.entity.Good;
import com.stepashka.jsptask.repository.GoodDao;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodService {
    private final GoodDao repository = new GoodDao();
    private static final Logger logger = Logger.getLogger(GoodService.class);

    public List<Good> findAll() {
        try {
            return repository.findAll();
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
        return new ArrayList<Good>();
    }

    public Good findById(Integer id) {
        try {
            return repository.findById(id);
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
        return new Good();
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

    public Integer save(Good entity){
        Integer changes = 0;
        try {
            changes = repository.save(entity);
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
        return changes;
    }

    public Integer update(Good entity){
        Integer changes = 0;
        try {
            changes = repository.update(entity);
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
        return changes;
    }
}
