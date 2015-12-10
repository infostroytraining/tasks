package com.example.dao;

import java.util.List;

public interface DAO<T> {

	T create(T entity);

	T get(int id);

	T update(T entity);

	void remove(int id);

	List<T> getAll();
}
