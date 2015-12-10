package com.example.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.dao.AnswerDAO;
import com.example.entity.Answer;

public class AnswerService {

	private AnswerDAO answerDAO;

	public AnswerService(AnswerDAO answerDAO) {
		this.answerDAO = answerDAO;
	}

	public Answer add(Answer answer) {
		Answer createdAnswer = null;
		if (answer != null) {
			createdAnswer = answerDAO.create(answer);
		}
		return createdAnswer;
	}

	public List<Answer> getAll() {
		return answerDAO.getAll();
	}

	/**
	 * This method returns a map with programming language name as a key and
	 * count of answers for this language as a value.
	 */
	public Map<String, Integer> getStatisticForEachAnswer() {
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
					if (answer != null && language.equals(answer.getLanguage())) {
						answersCount += 1;
					}
					statisticMap.put(language, answersCount);
				}
			}
		}
		return statisticMap;
	}
}
