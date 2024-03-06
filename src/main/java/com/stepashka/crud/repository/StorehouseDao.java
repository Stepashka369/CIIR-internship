package com.stepashka.crud.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stepashka.crud.datbase.DataSource;
import com.stepashka.crud.entity.Storehouse;

public class StorehouseDao implements AbstractDao<Storehouse>{
	private static final String findByIdSql = "SELECT * FROM storehouse WHERE id=?";
	private static final String findAllSql = "SELECT * FROM storehouse";
	private static final String saveSql = "INSERT INTO storehouse(address, square) VALUES(?, ?)";
	private static final String deleteByIdSql = "DELETE FROM storehouse WHERE id=?"; 
	private static final String updateSql = "UPDATE storehouse SET address=?, square=? WHERE id=?";
	
	@Override
	public Storehouse findById(Integer id) throws SQLException{
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(findByIdSql)){
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Storehouse storehouse = new Storehouse();
			result.next();
			storehouse.setId(result.getInt("id"));
			storehouse.setAddress(result.getString("address"));
			storehouse.setSquare(result.getFloat("square"));
			return storehouse;
		} 
	}

	@Override
	public List<Storehouse> findAll() throws SQLException{
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(findAllSql)){
			ResultSet result = statement.executeQuery();
			List<Storehouse> storehouseList = new ArrayList<>();
			while(result.next()) {
				Storehouse storehouse = new Storehouse();
				storehouse.setId(result.getInt("id"));
				storehouse.setAddress(result.getString("address"));
				storehouse.setSquare(result.getFloat("square"));
				storehouseList.add(storehouse);
			}
			return storehouseList;
		}
	}

	@Override
	public Integer save(Storehouse entity) throws SQLException{
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(saveSql)){
			statement.setString(1, entity.getAddress());
			statement.setFloat(2, entity.getSquare());
			return statement.executeUpdate();
		} 
	}

	@Override
	public Integer update(Storehouse entity) throws SQLException{
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(updateSql)){
			statement.setString(1, entity.getAddress());
			statement.setFloat(2, entity.getSquare());
			statement.setInt(3, entity.getId());
			return statement.executeUpdate();
		} 
	}

	@Override
	public Integer delete(Integer id) throws SQLException{
		try(Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(deleteByIdSql)){
			statement.setInt(1, id);
			return statement.executeUpdate();
		} 
	}
}
