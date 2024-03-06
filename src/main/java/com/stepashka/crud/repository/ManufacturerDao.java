package com.stepashka.crud.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.stepashka.crud.datbase.DataSource;
import com.stepashka.crud.entity.Good;
import com.stepashka.crud.entity.Manufacturer;
import com.stepashka.crud.entity.Storehouse;

public class ManufacturerDao implements AbstractDao<Manufacturer>{
	private static final String findByIdSql = "SELECT t1.id AS manufacturer_id, t1.manufacturer_name, t1.country, "
			+ "t2.id AS good_id, t2.product_name, t2.model, t2.guarantee, t2.price, t2.description "
			+ "FROM manufacturer AS t1 LEFT JOIN good AS t2 ON t2.id_manufacturer=t1.id WHERE t1.id=?";
	private static final String findAllSql = "SELECT * FROM manufacturer"; 
	private static final String saveSql = "INSERT INTO manufacturer(manufacturer_name, country) VALUES(?, ?)";
	private static final String updateSql = "UPDATE manufacturer SET manufacturer_name=?, country=? WHERE id=?";
	private static final String deleteSql = "DELETE FROM manufacturer WHERE id=?";

	@Override
	public Manufacturer findById(Integer id) throws SQLException {
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(findByIdSql)){
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Manufacturer manufacturer = new Manufacturer();
			Set<Good> goods = new HashSet<>();
			result.next();
			manufacturer.setId(result.getInt("manufacturer_id"));
			manufacturer.setName(result.getString("manufacturer_name"));
			manufacturer.setCountry(result.getString("country"));
			while(!result.isAfterLast() && result.getInt("good_id") != 0) {
				Good good = new Good();
				good.setId(result.getInt("good_id"));
				good.setName(result.getString("product_name"));
				good.setModel(result.getString("model"));
				good.setGuarantee(result.getInt("guarantee"));
				good.setPrice(result.getFloat("price"));
				good.setDescription(result.getString("description"));
				goods.add(good);
				result.next();
			}
			manufacturer.setGoods(goods);
		
			return manufacturer;
		} 
	}

	@Override
	public List<Manufacturer> findAll() throws SQLException {
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(findAllSql)){
			ResultSet result = statement.executeQuery();
			List<Manufacturer> manufacturerList = new ArrayList<>();
			while(result.next()) {
				Manufacturer manufacturer = new Manufacturer();
				manufacturer.setId(result.getInt("id"));
				manufacturer.setCountry(result.getString("country"));
				manufacturer.setName(result.getString("manufacturer_name"));
				manufacturerList.add(manufacturer);
			}
			
			return manufacturerList;
		}	
	}

	@Override
	public Integer save(Manufacturer entity) throws SQLException {
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(saveSql)){
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getCountry());
			return statement.executeUpdate();
		}
	}

	@Override
	public Integer update(Manufacturer entity) throws SQLException {
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(updateSql)){
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getCountry());
			statement.setInt(3, entity.getId());
			return statement.executeUpdate();
		}
	}

	@Override
	public Integer delete(Integer id) throws SQLException {
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(deleteSql)){
			statement.setInt(1, id);
			return statement.executeUpdate();
		}
	}
}
