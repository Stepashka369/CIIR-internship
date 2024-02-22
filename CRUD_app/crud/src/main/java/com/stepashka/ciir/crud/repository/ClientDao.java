package com.stepashka.ciir.crud.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.stepashka.ciir.crud.entity.Client;

public class ClientDao implements AbstractDao<Client>{
	private String sqlCreate = "INSERT INTO client (first_name, last_name, phone_number, address) VALUES (?, ?, ?, ?)";
	
	@Override
	public void create(Client entity) {
		Connection connection = ConnectionManager.getInstance().openConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sqlCreate);
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setString(3, entity.getPhoneNumber());
			statement.setString(4, entity.getAddress());
			statement.executeUpdate();
		} catch (SQLException exception) {
			// TODO loging
		}
		
	}

	@Override
	public Client read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Client entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
