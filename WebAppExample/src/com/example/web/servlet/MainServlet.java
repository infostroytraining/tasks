package com.example.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.dto.AnswerDTO;
import com.example.entity.Answer;
import com.example.service.AnswerService;
import com.example.service.exception.ServiceException;
import com.google.common.base.Strings;

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
			req.setAttribute("statisticMap", answerService.getStatisticForEachAnswer());
			req.setAttribute("answers", answerService.getAll());
		} catch (ServiceException e) {
			log.error("Exception in servlet", e);
			throw new ServletException(e);
		}
		req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String language = req.getParameter("language");
		AnswerDTO answer = new AnswerDTO(name, language);
		Map<String, String> errors = validateForm(answer);
		try {
			if (!errors.isEmpty()) {
				req.setAttribute("answer", answer);
				req.setAttribute("errors", errors);
			} else {
				answerService.add(new Answer(name, language));
			}
			req.setAttribute("statisticMap", answerService.getStatisticForEachAnswer());
			req.setAttribute("answers", answerService.getAll());
			req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
		} catch (ServiceException e) {
			log.error("Exception in servlet", e);
			throw new ServletException(e);
		}
	}

	protected Map<String, String> validateForm(AnswerDTO answer) {
		Map<String, String> errors = new HashMap<>();

		if (Strings.isNullOrEmpty(answer.getName())) {
			errors.put("name", "Please, input your name");
		}

		if (Strings.isNullOrEmpty(answer.getLanguage())) {
			errors.put("language", "Please, input your favorite programming language.");
		}
		return errors;
	}
}
