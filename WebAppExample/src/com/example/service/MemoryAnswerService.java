package com.example.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.dao.AnswerDAO;
import com.example.dao.exception.DAOException;
import com.example.entity.Answer;
import com.example.service.exception.ServiceException;

public class MemoryAnswerService implements AnswerService {

	private AnswerDAO answerDAO;

	public MemoryAnswerService(AnswerDAO answerDAO) {
		this.answerDAO = answerDAO;
	}

	public List<Answer> getAll() throws ServiceException {
		try {
			return answerDAO.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Answer add(Answer answer) throws ServiceException {
		Answer createdAnswer = null;
		if (answer != null) {
			try {
				createdAnswer = answerDAO.create(answer);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return createdAnswer;
	}

	/**
	 * This method returns a map with programming language name as a key and
	 * count of answers for this language as a value.
	 * 
	 * @throws DAOException
	 */
	public Map<String, Integer> getStatisticForEachAnswer() throws ServiceException {
		Map<String, Integer> statisticMap = new HashMap<>();
		Set<String> languages = new HashSet<>();

		for (Answer answer : getAll()) {
			if (answer != null) {
				languages.add(answer.getLanguage());
			}
		}
		for (String language : languages) {
			int answersCount = 0;
			if (language != null) {
				for (Answer answer : getAll()) {
					if (answer != null && language.equalsIgnoreCase(answer.getLanguage())) {
						answersCount += 1;
					}
					statisticMap.put(language.toLowerCase(), answersCount);
				}
			}
		}
		return statisticMap;
	}

	@Override
	public void remove(int id) throws ServiceException {
		answerDAO.remove(id);
	}
}
