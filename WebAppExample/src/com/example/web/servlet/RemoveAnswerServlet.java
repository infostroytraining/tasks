package com.example.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Integers;

import com.example.service.AnswerService;
import com.example.service.exception.ServiceException;

public class RemoveAnswerServlet extends HttpServlet {

	private static final Logger log = LogManager.getLogger();
	private AnswerService answerService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		answerService = (AnswerService) config.getServletContext().getAttribute("answerService");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String answerId = req.getParameter("id");
		int id = Integers.parseInt(answerId);
		log.debug("answer id to remove {}", id);
		try {
			answerService.remove(id);
		} catch (ServiceException e) {
			throw new ServletException();
		}
	}
}
