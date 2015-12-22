package com.example.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.dto.AnswerDTO;
import com.example.entity.Answer;
import com.example.service.AnswerService;
import com.example.service.exception.ServiceException;
import com.google.common.base.Strings;
import com.google.gson.Gson;

public class AddAnswerServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger();

	private AnswerService answerService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		answerService = (AnswerService) config.getServletContext().getAttribute("answerService");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String language = req.getParameter("language");
		AnswerDTO answer = new AnswerDTO(name, language);
		Map<String, String> errors = validateForm(answer);
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		try {
			if (!errors.isEmpty()) {
				resp.setStatus(400);
				resp.getWriter().write(new Gson().toJson(errors));
			} else {
				answerService.add(new Answer(name, language));
				resp.getWriter().write(new Gson().toJson(answerService.getAll()));
			}
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
