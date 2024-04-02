package com.stepashka.jsptask.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.stepashka.jsptask.database.DataSource;
import com.stepashka.jsptask.entity.Good;
import com.stepashka.jsptask.entity.Storehouse;

public class StorehouseDao implements AbstractDao<Storehouse> {
	private static final String FIND_BY_ID_SQL = "SELECT t1.id AS storehouse_id, t1.address, t1.square, t3.id AS good_id, t3.product_name, t3.model, t3.guarantee, t3.price, t3.description, t2.good_num FROM storehouse AS t1 LEFT JOIN good_storehouse AS t2 ON t1.id=t2.id_stock LEFT JOIN good AS t3 ON t2.id_good=t3.id WHERE t1.id=?";
	private static final String FIND_ALL_SQL = "SELECT t1.id AS storehouse_id, t1.address, t1.square, t3.id AS good_id, t3.product_name, t3.model, t3.guarantee, t3.price, t3.description, t2.good_num FROM storehouse AS t1 LEFT JOIN good_storehouse AS t2 ON t1.id=t2.id_stock LEFT JOIN good AS t3 ON t2.id_good=t3.id ORDER BY t1.id";
	private static final String SAVE_SQL = "INSERT INTO storehouse(address, square) VALUES(?, ?)";
	private static final String DELETE_BY_ID_SQL = "DELETE FROM storehouse WHERE id=?";
	private static final String UPDATE_SQL = "UPDATE storehouse SET address=?, square=? WHERE id=?";
	private static final String COLUMN_GOOD_ID = "good_id";
	private static final String COLUMN_GOOD_NUM = "good_num";
	private static final String COLUMN_GOOD_NAME = "product_name";
	private static final String COLUMN_GOOD_MODEL = "model";
	private static final String COLUMN_GOOD_GUARANTEE = "guarantee";
	private static final String COLUMN_GOOD_PRICE = "price";
	private static final String COLUMN_GOOD_DESCRIPTION = "description";
	private static final String COLUMN_STOREHOUSE_ID = "storehouse_id";
	private static final String COLUMN_STOREHOUSE_ADDRESS = "address";
	private static final String COLUMN_STOREHOUSE_SQUARE = "square";

	@Override
	public Storehouse findById(Integer id) throws SQLException {
		try (Connection connection = DataSource.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Storehouse storehouse = new Storehouse();
			result.next();
			storehouse.setId(result.getInt(COLUMN_STOREHOUSE_ID));
			storehouse.setAddress(result.getString(COLUMN_STOREHOUSE_ADDRESS));
			storehouse.setSquare(result.getFloat(COLUMN_STOREHOUSE_SQUARE));
			storehouse.setGoods(new HashMap<>());
			while (!result.isAfterLast() && result.getInt(COLUMN_GOOD_ID) != 0) {
				Good good = new Good();
				good.setId(result.getInt(COLUMN_GOOD_ID));
				good.setName(result.getString(COLUMN_GOOD_NAME));
				good.setModel(result.getString(COLUMN_GOOD_MODEL));
				good.setGuarantee(result.getInt(COLUMN_GOOD_GUARANTEE));
				good.setPrice(result.getFloat(COLUMN_GOOD_PRICE));
				good.setDescription(result.getString(COLUMN_GOOD_DESCRIPTION));
				storehouse.getGoods().put(good, result.getInt(COLUMN_GOOD_NUM));
				result.next();
			}
			return storehouse;
		}
	}

	@Override
	public List<Storehouse> findAll() throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL,
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet result = statement.executeQuery();
			List<Storehouse> storehouseList = new ArrayList<>();
			while (result.next()) {
				Boolean hasGood = false;
				Integer currentId = result.getInt(COLUMN_STOREHOUSE_ID);
				Storehouse storehouse = new Storehouse();
				storehouse.setId(currentId);
				storehouse.setAddress(result.getString(COLUMN_STOREHOUSE_ADDRESS));
				storehouse.setSquare(result.getFloat(COLUMN_STOREHOUSE_SQUARE));
				storehouse.setGoods(new HashMap<>());
				while (!result.isAfterLast() && result.getInt(COLUMN_STOREHOUSE_ID) == currentId && result.getInt(COLUMN_GOOD_ID) != 0) {
					hasGood = true;
					Good good = new Good();
					good.setId(result.getInt(COLUMN_GOOD_ID));
					good.setName(result.getString(COLUMN_GOOD_NAME));
					good.setModel(result.getString(COLUMN_GOOD_MODEL));
					good.setGuarantee(result.getInt(COLUMN_GOOD_GUARANTEE));
					good.setPrice(result.getFloat(COLUMN_GOOD_PRICE));
					good.setDescription(result.getString(COLUMN_GOOD_DESCRIPTION));
					storehouse.getGoods().put(good, result.getInt(COLUMN_GOOD_NUM));
					result.next();
				}
				if (hasGood.equals(true)) {
					result.previous();
				}
				storehouseList.add(storehouse);
			}
			return storehouseList;
		}
	}

	@Override
	public Integer save(Storehouse entity) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SAVE_SQL)) {
			statement.setString(1, entity.getAddress());
			statement.setFloat(2, entity.getSquare());
			return statement.executeUpdate();
		}
	}

	@Override
	public Integer update(Storehouse entity) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
			statement.setString(1, entity.getAddress());
			statement.setFloat(2, entity.getSquare());
			statement.setInt(3, entity.getId());
			return statement.executeUpdate();
		}
	}

	@Override
	public Integer delete(Integer id) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
			statement.setInt(1, id);
			return statement.executeUpdate();
		}
	}
}
