package com.stepashka.crud.repository;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDao<T> {
	public T findById(Integer id) throws SQLException;
	public List<T> findAll() throws SQLException;
	public Integer save(T entity) throws SQLException;
	public Integer update(T entity) throws SQLException;
	public Integer delete(Integer id) throws SQLException;
}