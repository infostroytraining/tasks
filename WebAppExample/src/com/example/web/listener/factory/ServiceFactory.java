package com.example.web.listener.factory;

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

	public static AnswerService getAnswerService(String serviceType) {
		if (serviceType == null || serviceType.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (serviceType.equals(MEMORY)) {
			return initMemoryService();
		} else if (serviceType.equals(DB)) {
			return initTransactionalService();
		} else {
			throw new IllegalArgumentException();
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
