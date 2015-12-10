package com.example.dao;

import java.util.List;

import com.example.entity.Answer;

public interface AnswerDAO extends DAO<Answer> {
	
	Answer getAnswerByUserName(String userName);

	List<Answer> getAnswerByLanguage(String language);
}
