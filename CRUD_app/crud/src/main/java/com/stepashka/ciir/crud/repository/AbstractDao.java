package com.stepashka.ciir.crud.repository;

public interface AbstractDao<T> {
	public void create(final T entity);
	public T read(final Integer id);
	public void update(final T entity);
	public void delete(final Integer id);
//	public T getById(final Long id);
//	public List<T> getAll();
//	public T save(final T entity);
//	public T update(final T entity);
//	public void delete(final T entity);
//	public void deleteById(final Long entityId);
}
