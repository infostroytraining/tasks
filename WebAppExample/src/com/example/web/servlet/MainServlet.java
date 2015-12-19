package com.example.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	} 

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = req.getServletContext();
		AnswerService answerService = (AnswerService) context.getAttribute("answerService");
		String name = req.getParameter("name"); 
		String language = req.getParameter("language");
		AnswerDTO answer = new AnswerDTO(name, language);
		List<String> errors = validateForm(answer);
		if (!CollectionUtils.isEmpty(errors)) {
			req.setAttribute("answer", answer);
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		} else {
			try {
				answerService.add(new Answer(name, language));
				req.setAttribute("statisticMap", answerService.getStatisticForEachAnswer());
			} catch (ServiceException e) {
				log.error("Exception in servlet", e);
				throw new ServletException(e);
			}
			req.setAttribute("name", name);
			req.getRequestDispatcher("answer.jsp").forward(req, resp);
		}
	}

	protected List<String> validateForm(AnswerDTO answer) {
		List<String> errors = new ArrayList<>();

		if (Strings.isNullOrEmpty(answer.getName())) {
			errors.add("Please, input your name");
		}
		
		if (Strings.isNullOrEmpty(answer.getLanguage())) {
			errors.add("Please, input your language.");
		}
		return errors;
	}
}
