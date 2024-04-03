package com.stepashka.jsptask.repository;

import com.stepashka.jsptask.database.DataSource;
import com.stepashka.jsptask.entity.Storehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class GoodStorehouseDao implements AbstractDao<Storehouse> {
    private static final String SAVE_SQL = "INSERT INTO good_storehouse(id_good, id_stock, good_num) VALUES(?, ?, ?)";

    @Override
    public Storehouse findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Storehouse> findAll() throws SQLException {
        return null;
    }

    @Override
    public Integer save(Storehouse entity) throws SQLException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL)) {
            statement.setInt(1, entity.getGoods().keySet().stream().collect(Collectors.toList()).get(0).getId());
            statement.setInt(2, entity.getId());
            statement.setInt(3, entity.getGoods().values().stream().collect(Collectors.toList()).get(0).intValue());
            return statement.executeUpdate();
        }
    }

    @Override
    public Integer update(Storehouse entity) throws SQLException {
        return null;
    }

    @Override
    public Integer delete(Integer id) throws SQLException {
        return null;
    }
}
