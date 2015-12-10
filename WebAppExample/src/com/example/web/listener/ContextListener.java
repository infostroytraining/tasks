package com.example.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.example.dao.AnswerDAO;
import com.example.dao.memory.MemoryAnswerDAO;
import com.example.dao.storage.AnswerStorage;
import com.example.service.AnswerService;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		AnswerStorage storage = new AnswerStorage();
		AnswerDAO answerDAO = new MemoryAnswerDAO(storage);
		AnswerService answerService = new AnswerService(answerDAO);
		sce.getServletContext().setAttribute("answerService", answerService);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// we need to add log there
	}
}
