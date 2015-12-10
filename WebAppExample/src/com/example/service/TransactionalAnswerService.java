package com.example.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.dao.AnswerDAO;
import com.example.db.Transaction;
import com.example.db.TransactionManager;
import com.example.db.exception.TransactionException;
import com.example.entity.Answer;
import com.example.service.exception.ServiceException;

public class TransactionalAnswerService implements AnswerService {

	private TransactionManager transactionManager;
	private AnswerDAO answerDAO;

	public TransactionalAnswerService(TransactionManager transactionManager, AnswerDAO answerDAO) {
		this.transactionManager = transactionManager;
		this.answerDAO = answerDAO;
	}

	@Override
	public Answer add(final Answer answer) throws ServiceException {
		try {
			return transactionManager.doTask(new Transaction<Answer>() {
				@Override
				public Answer execute() {
					return answerDAO.create(answer);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}
}
