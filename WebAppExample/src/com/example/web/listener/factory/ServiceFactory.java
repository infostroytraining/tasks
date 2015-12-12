package com.example.web.listener.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.dao.AnswerDAO;
import com.example.dao.memory.MemoryAnswerDAO;
import com.example.dao.postgesql.PostgreAnswerDAO;
import com.example.dao.storage.AnswerStorage;
import com.example.db.TransactionManager;
import com.example.service.AnswerService;
import com.example.service.MemoryAnswerService;
import com.example.service.TransactionalAnswerService;

public class ServiceFactory {

	public static final String MEMORY = "memory";
	public static final String DB = "db";
	private static final String POSTGRE_DRIVER = "org.postgresql.Driver";

	private static Logger logger = LogManager.getLogger();

	public static AnswerService getAnswerService(String type) {
		if (type == null || type.isEmpty()) {
			logger.fatal("Could initialize application. Source type is null or empty");
			throw new IllegalArgumentException();
		}
		if (type.equals(MEMORY)) {
			return initMemoryService();
		} else if (type.equals(DB)) {
			loadPostgreDriver();
			return initTransactionalService();
		} else {
			logger.fatal("Could initialize application with source type {}", type);
			throw new IllegalArgumentException();
		}
	}
	
	private static void loadPostgreDriver(){
		try {
			Class.forName(POSTGRE_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO handle exception
		}
	}

	private static AnswerService initMemoryService() {
		AnswerStorage storage = new AnswerStorage();
		AnswerDAO answerDAO = new MemoryAnswerDAO(storage);
		return new MemoryAnswerService(answerDAO);
	}

	private static AnswerService initTransactionalService() {
		TransactionManager transactionManager = new TransactionManager();
		AnswerDAO answerDAO = new PostgreAnswerDAO();
		return new TransactionalAnswerService(transactionManager, answerDAO);
	}
}
