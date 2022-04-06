package com.fiveand.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet{


	private HandlerMapping mappings = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {

		String propLoc = config.getInitParameter("propLocation");


		mappings = new HandlerMapping(propLoc);
		
	}
	
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		uri = uri.substring(contextPath.length());
		System.out.println("요청 URI : " + uri);
		
		
		try{

			Controller control = mappings.getController(uri);
				String callPage = control.handleRequest(request, response);

				if(callPage.startsWith("redirect:")) {
					callPage = callPage.substring("redirect:".length());
					response.sendRedirect(request.getContextPath() + callPage);

				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(callPage);
					dispatcher.forward(request, response);
				}
				
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} 
		
		
	}

	
}
