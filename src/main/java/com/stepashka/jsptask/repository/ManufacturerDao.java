package com.stepashka.jsptask.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.stepashka.jsptask.database.DataSource;
import com.stepashka.jsptask.entity.Good;
import com.stepashka.jsptask.entity.Manufacturer;

public class ManufacturerDao implements AbstractDao<Manufacturer> {
	private static final String FIND_BY_ID_SQL = "SELECT t1.id AS manufacturer_id, t1.manufacturer_name, t1.country, t2.id AS good_id, t2.product_name, t2.model, t2.guarantee, t2.price, t2.description FROM manufacturer AS t1 LEFT JOIN good AS t2 ON t1.id=t2.id_manufacturer WHERE t1.id=?";
	private static final String FIND_ALL_SQL = "SELECT t1.id AS manufacturer_id, t1.manufacturer_name, t1.country, t2.id AS good_id, t2.product_name, t2.model, t2.guarantee, t2.price, t2.description FROM manufacturer AS t1 LEFT JOIN good AS t2 ON t1.id=t2.id_manufacturer ORDER BY manufacturer_id";
	private static final String SAVE_SQL = "INSERT INTO manufacturer(manufacturer_name, country) VALUES(?, ?)";
	private static final String UPDATE_SQL = "UPDATE manufacturer SET manufacturer_name=?, country=? WHERE id=?";
	private static final String DELETE_SQL = "DELETE FROM manufacturer WHERE id=?";

	@Override
	public Manufacturer findById(Integer id) throws SQLException {
		try (Connection connection = DataSource.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Manufacturer manufacturer = new Manufacturer();
			result.next();
			manufacturer.setId(result.getInt("manufacturer_id"));
			manufacturer.setName(result.getString("manufacturer_name"));
			manufacturer.setCountry(result.getString("country"));
			manufacturer.setGoods(new HashSet<>());
			while (!result.isAfterLast() && result.getInt("good_id") != 0) {
				Good good = new Good();
				good.setId(result.getInt("good_id"));
				good.setName(result.getString("product_name"));
				good.setModel(result.getString("model"));
				good.setGuarantee(result.getInt("guarantee"));
				good.setPrice(result.getFloat("price"));
				good.setDescription(result.getString("description"));
				manufacturer.getGoods().add(good);
				result.next();
			}
			return manufacturer;
		}
	}

	@Override
	public List<Manufacturer> findAll() throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL,
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet result = statement.executeQuery();
			List<Manufacturer> manufacturerList = new ArrayList<>();
			while (result.next()) {
				Boolean hasGood = false;
				Integer currentId = result.getInt("manufacturer_id");
				Manufacturer manufacturer = new Manufacturer();
				manufacturer.setId(currentId);
				manufacturer.setCountry(result.getString("country"));
				manufacturer.setName(result.getString("manufacturer_name"));
				manufacturer.setGoods(new HashSet<>());
				while (!result.isAfterLast() && result.getInt("manufacturer_id") == currentId && result.getInt("good_id") != 0) {
					hasGood = true;
					Good good = new Good();
					good.setId(result.getInt("good_id"));
					good.setName(result.getString("product_name"));
					good.setModel(result.getString("model"));
					good.setGuarantee(result.getInt("guarantee"));
					good.setPrice(result.getFloat("price"));
					good.setDescription(result.getString("description"));
					manufacturer.getGoods().add(good);
					result.next();
				}
				if (hasGood) {
					result.previous();
				}
				manufacturerList.add(manufacturer);
			}
			return manufacturerList;
		}
	}

	@Override
	public Integer save(Manufacturer entity) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SAVE_SQL)) {
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getCountry());
			return statement.executeUpdate();
		}
	}

	@Override
	public Integer update(Manufacturer entity) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getCountry());
			statement.setInt(3, entity.getId());
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
