package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.db.exception.TransactionException;

public class TransactionManager {
	private static final String POSTGRE_DRIVER = "org.postgresql.Driver";
	private static final Logger log = LogManager.getLogger(TransactionManager.class);

	public <T> T doTask(Transaction<T> transaction, int transactionIsolation) throws TransactionException {
		Connection con = null;
		try {
			Class.forName(POSTGRE_DRIVER);
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/answerdb");
			ConnectionHolder.setConnection(con);
			con.setTransactionIsolation(transactionIsolation);
			T value = transaction.execute();
			con.commit();
			return value;
		} catch (ClassNotFoundException e) {
			log.error("Class not found exception for {}, message:", POSTGRE_DRIVER, e.getMessage());
			throw new TransactionException(e);
		} catch (SQLException e) {
			log.error("SQLException in TransactionManager class, message:", e.getMessage());
			throw new TransactionException(e);
		} finally {
			ConnectionHolder.setConnection(null);
			try {
				con.close();
			} catch (SQLException e) {
				log.error("Error while closing the connection", e);
				throw new TransactionException(e);
			}
		}
	}
}
