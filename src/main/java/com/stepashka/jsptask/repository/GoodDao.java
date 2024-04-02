package com.stepashka.jsptask.repository;

import com.stepashka.jsptask.database.DataSource;
import com.stepashka.jsptask.entity.Good;
import com.stepashka.jsptask.entity.Manufacturer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDao implements AbstractDao<Good> {
	private static final String FIND_BY_ID_SQL = "SELECT t1.id AS good_id, t1.product_name, t1.model, t1.guarantee, t1.price,t1.description, t2.id AS manufacturer_id, t2.manufacturer_name, t2.country FROM good AS t1 LEFT JOIN manufacturer AS t2 ON t1.id_manufacturer=t2.id WHERE t1.id=?";
	private static final String FIND_ALL_SQL = "SELECT t1.id AS good_id, t1.product_name, t1.model, t1.guarantee, t1.price,t1.description, t2.id AS manufacturer_id, t2.manufacturer_name, t2.country FROM good AS t1 LEFT JOIN manufacturer AS t2 ON t1.id_manufacturer=t2.id";
	private static final String SAVE_SQL = "INSERT INTO good(product_name, model, guarantee, price, description, id_manufacturer) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_SQL = "UPDATE good SET product_name=?, model=?, guarantee=?, price=?, description=? WHERE id=?";
	private static final String DELETE_SQL = "DELETE FROM good WHERE id=?";
	private static final String COLUMN_GOOD_ID = "good_id";
	private static final String COLUMN_GOOD_NAME = "product_name";
	private static final String COLUMN_GOOD_MODEL = "model";
	private static final String COLUMN_GOOD_GUARANTEE = "guarantee";
	private static final String COLUMN_GOOD_PRICE = "price";
	private static final String COLUMN_GOOD_DESCRIPTION = "description";
	private static final String COLUMN_MANUFACTURER_ID = "manufacturer_id";
	private static final String COLUMN_MANUFACTURER_NAME = "manufacturer_name";
	private static final String COLUMN_MANUFACTURER_COUNTRY = "country";

	@Override
	public Good findById(Integer id) throws SQLException {
		try (Connection connection = DataSource.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Good good = new Good();
			good.setManufacturer(new Manufacturer());
			if (result.next()) {
				good.setId(result.getInt(COLUMN_GOOD_ID));
				good.setName(result.getString(COLUMN_GOOD_NAME));
				good.setModel(result.getString(COLUMN_GOOD_MODEL));
				good.setGuarantee(result.getInt(COLUMN_GOOD_GUARANTEE));
				good.setPrice(result.getFloat(COLUMN_GOOD_PRICE));
				good.setDescription(result.getString(COLUMN_GOOD_DESCRIPTION));
				good.getManufacturer().setId(result.getInt(COLUMN_MANUFACTURER_ID));
				good.getManufacturer().setName(result.getString(COLUMN_MANUFACTURER_NAME));
				good.getManufacturer().setCountry(result.getString(COLUMN_MANUFACTURER_COUNTRY));
			}
			return good;
		}
	}

	@Override
	public List<Good> findAll() throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
			ResultSet result = statement.executeQuery();
			List<Good> goodList = new ArrayList<>();
			while (result.next()) {
				Good good = new Good();
				good.setId(result.getInt(COLUMN_GOOD_ID));
				good.setName(result.getString(COLUMN_GOOD_NAME));
				good.setModel(result.getString(COLUMN_GOOD_MODEL));
				good.setGuarantee(result.getInt(COLUMN_GOOD_GUARANTEE));
				good.setPrice(result.getFloat(COLUMN_GOOD_PRICE));
				good.setDescription(result.getString(COLUMN_GOOD_DESCRIPTION));
				good.setManufacturer(new Manufacturer());
				good.getManufacturer().setId(result.getInt(COLUMN_MANUFACTURER_ID));
				good.getManufacturer().setName(result.getString(COLUMN_MANUFACTURER_NAME));
				good.getManufacturer().setCountry(result.getString(COLUMN_MANUFACTURER_COUNTRY));
				goodList.add(good);
			}
			return goodList;
		}
	}

	@Override
	public Integer save(Good entity) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SAVE_SQL)) {
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getModel());
			statement.setInt(3, entity.getGuarantee());
			statement.setFloat(4, entity.getPrice());
			statement.setString(5, entity.getDescription());
			statement.setInt(6, entity.getManufacturer().getId());
			return statement.executeUpdate();
		}
	}

	@Override
	public Integer update(Good entity) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getModel());
			statement.setInt(3, entity.getGuarantee());
			statement.setFloat(4, entity.getPrice());
			statement.setString(5, entity.getDescription());
			statement.setInt(6, entity.getId());
			return statement.executeUpdate();
		}
	}

	@Override
	public Integer delete(Integer id) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
			statement.setInt(1, id);
			return statement.executeUpdate();
		}
	}
}
