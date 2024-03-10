package com.stepashka.crud.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stepashka.crud.datbase.DataSource;
import com.stepashka.crud.entity.Good;
import com.stepashka.crud.entity.Manufacturer;
import com.stepashka.crud.entity.Storehouse;

public class GoodDao implements AbstractDao<Good>{
	private static final String FIND_ALL_SQL = "SELECT t1.id, t1.product_name, t1.model, t1.guarantee, t1.price, t1.description,"
			+ "t2.id AS manufacturer_id, t2.manufacturer_name, t2.country "
			+ "FROM good AS t1 LEFT JOIN manufacturer AS t2 ON t1.id_manufacturer=t2.id";
	private static final String SAVE_SQL = "INSERT INTO good(product_name, model, guarantee, price, description, id_manufacturer)"
			+ "VALUES(?, ?, ?, ?, ?, ?)";
	private static final String DELETE_SQL = "DELETE FROM good WHERE id=?";
	private static final String UPDATE_SQL = "UPDATE good SET product_name=?, model=?, guarantee=?, price=?, description=? WHERE id=?";
	private static final String FIND_BY_ID_SQL = "SELECT good.id AS good_id, good.product_name, good.model, good.guarantee, good.price, good.description, "
			+ "manufacturer.id AS manufacturer_id, manufacturer.manufacturer_name, manufacturer.country AS manufacturer_country, "
			+ "good_storehouse.good_num, storehouse.id AS storehouse_id, storehouse.address AS storehouse_address, storehouse.square AS storehouse_square "
			+ "FROM good_storehouse JOIN storehouse ON good_storehouse.id_stock=storehouse.id "
			+ "JOIN good ON good_storehouse.id_good=good.id "
			+ "JOIN manufacturer ON good.id_manufacturer=manufacturer.id WHERE good.id=?";
	
	@Override
	public Good findById(Integer id) throws SQLException {
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)){
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Good good = new Good();
			good.setManufacturer(new Manufacturer());
			good.setStorehouses(new HashMap<Storehouse, Integer>());
			if(result.next()) {
				good.setId(result.getInt("good_id"));
				good.setName(result.getString("product_name"));
				good.setModel(result.getString("model"));
				good.setGuarantee(result.getInt("guarantee"));
				good.setPrice(result.getFloat("price"));
				good.setDescription(result.getString("description"));
				good.getManufacturer().setId(result.getInt("manufacturer_id"));
				good.getManufacturer().setName(result.getString("manufacturer_name"));
				good.getManufacturer().setCountry(result.getString("manufacturer_country"));
				while(!result.isAfterLast() && result.getInt("good_id") != 0) {
					Storehouse storehouse = new Storehouse();
					storehouse.setId(result.getInt("storehouse_id"));
					storehouse.setAddress(result.getString("storehouse_address"));
					storehouse.setSquare(result.getFloat("storehouse_square"));
					good.getStorehouses().put(storehouse, result.getInt("good_num"));
					result.next();
				}
			}
			return good;
		}
	}

	@Override
	public List<Good> findAll() throws SQLException {
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)){
			ResultSet result = statement.executeQuery();
			List<Good> goodList = new ArrayList<>();
			while(result.next()) {
				Good good = new Good();
				good.setId(result.getInt("id"));
				good.setName(result.getString("product_name"));
				good.setModel(result.getString("model"));
				good.setGuarantee(result.getInt("guarantee"));
				good.setPrice(result.getFloat("price")); 
				good.setDescription(result.getString("description"));
				Manufacturer manufacturer = new Manufacturer();
				manufacturer.setId(result.getInt("manufacturer_id"));
				manufacturer.setName(result.getString("manufacturer_name"));
				manufacturer.setCountry(result.getString("country"));
				good.setManufacturer(manufacturer);
				goodList.add(good);
			}
			return goodList;
		}
	}

	@Override
	public Integer save(Good entity) throws SQLException {
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SAVE_SQL)){
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
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)){
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
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SQL)){
			statement.setInt(1, id);
			return statement.executeUpdate();
		}
	}
}
