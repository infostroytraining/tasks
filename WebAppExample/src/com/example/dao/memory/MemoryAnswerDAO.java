package com.example.dao.memory;

import java.util.List;

import com.example.dao.AnswerDAO;
import com.example.dao.storage.AnswerStorage;
import com.example.entity.Answer;

public class MemoryAnswerDAO implements AnswerDAO {

	private AnswerStorage storage;
	
	public MemoryAnswerDAO(AnswerStorage storage) {
		this.storage = storage;
	}
	
	@Override
	public Answer create(Answer answer) {
		return storage.add(answer);
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
		return storage.all();
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
