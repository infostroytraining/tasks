package com.example.db;

public interface Transaction<T> {

	public T execute();

}
