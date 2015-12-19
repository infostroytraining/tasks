package com.example.web.servlet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import com.example.dto.AnswerDTO;
import com.example.entity.Answer;
import com.example.service.AnswerService;
import com.example.service.exception.ServiceException;

public class MainServletTest {

	private static final String JAVA = "Java";
	private static final String JOHN = "John";

	private MainServlet servlet = new MainServlet();
	
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private ServletContext context;
	@Mock
	private AnswerService service;
	@Mock
	private RequestDispatcher dispatcher;
	
	@Before
	public void init(){
		when(request.getServletContext()).thenReturn(context);
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		when(context.getAttribute("answerService")).thenReturn(service);

	}
	
	@Test
	public void testDoGet() throws ServletException, IOException{
		servlet.doGet(request, response);
		verify(request).getRequestDispatcher("home.jsp");
	}
	
	@Test
	public void testDoPost() throws ServletException, IOException, ServiceException{
		mockGetRequestParams(JOHN, JAVA);
		servlet.doPost(request, response);
		ArgumentCaptor<Answer> argument = ArgumentCaptor.forClass(Answer.class);
		verify(service).add(argument.capture());
		
		Answer answer = argument.getValue();
		assertEquals(JOHN, answer.getName());
		assertEquals(JAVA, answer.getLanguage());
		
		verify(service).getStatisticForEachAnswer();
		verify(request).setAttribute(eq("statisticMap"), anyMap());
		verify(request).setAttribute(eq("name"), anyString());
	}
	
	@Test(expected=ServletException.class)
	public void testDoPostWithServiceException() throws ServletException, IOException, ServiceException{
		mockGetRequestParams(JOHN, JAVA);
		when(service.add(any(Answer.class))).thenThrow(ServiceException.class);
		servlet.doPost(request, response);
	}
	
	@Test
	public void testDoPostWithEmptyParamsFromRequest() throws ServletException, IOException{
		mockGetRequestParams(Strings.EMPTY, Strings.EMPTY);
		servlet.doPost(request, response);
		verify(request).setAttribute(eq("answer"), any(Answer.class));
		verify(request).setAttribute(eq("errors"), anyList());
	}
	
	@Test
	public void testValidateForm(){
		AnswerDTO answer = new AnswerDTO(JOHN, JAVA);
		List<String> result = servlet.validateForm(answer);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void testValidateFormWithErrors(){
		AnswerDTO answer = new AnswerDTO(null, null);
		List<String> result = servlet.validateForm(answer);
		assertFalse(result.isEmpty());
	}

	private void mockGetRequestParams(String name, String language){
		when(request.getParameter("name")).thenReturn(name);
		when(request.getParameter("language")).thenReturn(language);
	}
}
