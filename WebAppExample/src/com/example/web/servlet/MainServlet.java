package com.example.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dto.AnswerDTO;
import com.example.entity.Answer;
import com.example.service.MemoryAnswerService;
import com.example.service.exception.ServiceException;

public class MainServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("date", new Date());
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MemoryAnswerService answerService = (MemoryAnswerService) req.getServletContext().getAttribute("answerService");

		String name = req.getParameter("name");
		String language = req.getParameter("language");
		AnswerDTO answer = new AnswerDTO(name, language);
		List<String> errors = validateForm(answer);
		if (!errors.isEmpty()) {
			req.setAttribute("answer", answer);
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		} else {
			try {
				answerService.add(new Answer(name, language));
			} catch (ServiceException e) {
				req.getRequestDispatcher("error.jsp");
			}
			req.setAttribute("statisticMap", answerService.getStatisticForEachAnswer());
			req.setAttribute("name", name);
			req.getRequestDispatcher("answer.jsp").forward(req, resp);
		}
	}

	private List<String> validateForm(AnswerDTO answer) {
		List<String> errors = new ArrayList<>();

		if (answer.getName() == null || answer.getName().isEmpty()) {
			errors.add("Please, input your name");
		}

		if (answer.getLanguage() == null || answer.getLanguage().isEmpty()) {
			errors.add("Please, input your language");
		}
		return errors;
	}
}
