package com.example.service;

import com.example.entity.Answer;
import com.example.service.exception.ServiceException;

public interface AnswerService {

	public Answer add(Answer answer) throws ServiceException;
}
