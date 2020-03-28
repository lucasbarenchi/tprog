package com.uytube.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getHeader("User-Agent").indexOf("Mobile") != -1) {
			
			if (request.getSession().getAttribute("usuario_logueado") != null) { // Si está logueado va a la home
				response.setContentType("text/html");
				request.getRequestDispatcher("/WEB-INF/mobile/home.jsp").include(request, response);
			} else {
				response.setContentType("text/html");
				request.setAttribute("visible", "hidden");
				request.setAttribute("userRemembered", request.getSession().getAttribute("userRemembered"));
				request.getRequestDispatcher("/WEB-INF/mobile/iniciar.jsp").include(request, response);
			}
		} else {
			response.setContentType("text/html");
			request.setAttribute("visible", "hidden");
			request.getRequestDispatcher("/WEB-INF/home/iniciar.jsp").include(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
