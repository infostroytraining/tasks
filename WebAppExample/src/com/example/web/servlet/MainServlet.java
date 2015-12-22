package com.example.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.service.AnswerService;
import com.example.service.exception.ServiceException;

public class MainServlet extends HttpServlet {

	private static final Logger log = LogManager.getLogger();

	private AnswerService answerService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		answerService = (AnswerService) config.getServletContext().getAttribute("answerService");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("answers", answerService.getAll());
		} catch (ServiceException e) {
			log.error("Exception in servlet", e);
			throw new ServletException(e);
		}
		req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
	}
}
