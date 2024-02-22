package com.stepashka.crud.repository;

public interface AbstractDao<T> {
	public void create(final T entity);
	public T read(final Integer id);
	public void update(final T entity);
	public void delete(final Integer id);
}