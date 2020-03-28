package com.uytube.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.server.implementation.UserWebService;
import com.main.server.implementation.UserWebServiceImplService;
import com.main.server.implementation.EntityNotFoundException;
import com.main.server.service.HistoryDT;

import java.util.List;

public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public History() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String nickname = (String)request.getSession().getAttribute("usuario_logueado");

		if (nickname == null) {
			// NO HAY USUARIO LOGUEADO
			request.setAttribute("titulo", "¡Error!");
			request.setAttribute("msg", "Debes tener tu cuenta abierta para ver el historial.");
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").include(request, response);
		} else {
			UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();
	    	
	    	try {
	    		List<HistoryDT> hist = userWebService.getUserHistory(nickname).getItem();
	    		request.setAttribute("history_data", hist);
	    		request.getRequestDispatcher("/WEB-INF/playlist/historyInfo.jsp").include(request, response);
	    	}catch(EntityNotFoundException e) {
	    		
	    	}
		}
	}
	
}
