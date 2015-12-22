package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.entity.Answer;
import com.example.service.exception.ServiceException;

public interface AnswerService {

	public Answer add(Answer answer) throws ServiceException;

	public Map<String, Integer> getStatisticForEachAnswer() throws ServiceException;

	public List<Answer> getAll() throws ServiceException;

	public void remove(int id) throws ServiceException;
}
