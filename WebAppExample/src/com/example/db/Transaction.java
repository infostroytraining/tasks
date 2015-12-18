package com.example.db;

import com.example.dao.exception.DAOException;

@FunctionalInterface
public interface Transaction<T> {

	T execute() throws DAOException;

}
