package com.example.dao.postgesql;

import java.sql.Connection;
import java.util.List;

import com.example.dao.AnswerDAO;
import com.example.db.ConnectionHolder;
import com.example.entity.Answer;

public class PostgreAnswerDAO implements AnswerDAO{

	private static final String INSERT_ANSWER = "INSERT INTO anwers";
	
	@Override
	public Answer create(Answer entity) {
		Connection connection = ConnectionHolder.getConnection();
		
		return null;
	}

	@Override
	public Answer get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer update(Answer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Answer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer getAnswerByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Answer> getAnswerByLanguage(String language) {
		// TODO Auto-generated method stub
		return null;
	}

}
