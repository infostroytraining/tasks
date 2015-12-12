package com.example.dao;

import java.util.List;

import com.example.dao.exception.DAOException;

public interface DAO<T> {

	T create(T entity) throws DAOException;

	T get(int id);

	T update(T entity);

	void remove(int id);

	List<T> getAll() throws DAOException;
}
