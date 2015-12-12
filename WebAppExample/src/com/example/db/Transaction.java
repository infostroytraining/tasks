package com.example.db;

import com.example.dao.exception.DAOException;

public interface Transaction<T> {

	public T execute() throws DAOException;

}
