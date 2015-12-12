package com.example.service;

import java.util.Map;

import com.example.entity.Answer;
import com.example.service.exception.ServiceException;

public interface AnswerService {

	public Answer add(Answer answer) throws ServiceException;

	public Map<String, Integer> getStatisticForEachAnswer() throws ServiceException;
}
