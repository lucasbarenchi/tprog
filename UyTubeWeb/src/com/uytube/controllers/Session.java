package com.uytube.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.main.server.implementation.UserWebServiceImplService;
import com.main.server.service.UserDT;
import com.main.server.implementation.EntityNotFoundException;
import com.main.server.implementation.UserWebService;
import com.uytube.model.EstadoSesion;

public class Session extends HttpServlet{

	public Session() {
		super();
	}
	
	protected void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession objSesion = request.getSession();
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		EstadoSesion nuevoEstado;
		boolean userValid = true;
		
		UserWebServiceImplService userService = new UserWebServiceImplService();
		UserWebService userWebService = userService.getUserWebServiceImplPort();
		
		try {
			if (!userWebService.verifyPassword(nickname, password)) {
				nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;
				request.getSession().setAttribute("userRemembered", nickname);
				request.setAttribute("userRemembered", nickname);
			}
			else {
				nuevoEstado = EstadoSesion.LOGIN_CORRECTO;
				UserDT userDT = userWebService.getUserData(nickname);
				request.getSession().setAttribute("usuario_logueado", userDT.getNickname());
				request.getSession().setAttribute("user_email", userDT.getMail());
				request.getSession().setAttribute("user_name", String.format("%s %s", userDT.getName(), userDT.getSurname()));
				
				if (request.getHeader("User-Agent").indexOf("Mobile") != -1) {
					if (request.getParameter("rememberMe") != null) {
						request.getSession().setAttribute("userRemembered", userDT.getNickname());
					} else {
						request.getSession().setAttribute("userRemembered", "");
					}
				}
			}
		} catch(EntityNotFoundException e) {
			nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;
			userValid = false;
		}
		objSesion.setAttribute("estado_sesion", nuevoEstado);
		switch (nuevoEstado) {
		case LOGIN_CORRECTO:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
			dispatcher.forward(request, response);
			break;
		case LOGIN_INCORRECTO:
			response.setContentType("text/html");
			if (!userValid) {
				request.setAttribute("userValid", "is-invalid");
			} else {
				request.setAttribute("passValid", "is-invalid");
			}
			
			if (request.getHeader("User-Agent").indexOf("Mobile") != -1) {
				
				request.getRequestDispatcher("/WEB-INF/mobile/iniciar.jsp").include(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/session/iniciarSesion.jsp").include(request, response);
			}
			
			break;
		}
	}
	
	protected void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession objSesion = request.getSession();
		objSesion.setAttribute("usuario_logueado", null);
		objSesion.setAttribute("user_email", null);
		objSesion.setAttribute("user_name", null);
		objSesion.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
		dispatcher.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getRequestURI()) {
		case "/iniciar_sesion":
			if (request.getHeader("User-Agent").indexOf("Mobile") != -1) {
				response.setContentType("text/html");
				request.setAttribute("userRemembered", request.getSession().getAttribute("userRemembered"));
				request.getRequestDispatcher("/WEB-INF/mobile/iniciar.jsp").include(request, response);
			} else {
				response.setContentType("text/html");
				request.setAttribute("userValid", "is-valid");
				request.getRequestDispatcher("/WEB-INF/session/iniciarSesion.jsp").include(request, response);
			}
			
			break;
		case "/cerrar_sesion":
			cerrarSesion(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getRequestURI()) {
		case "/iniciar_sesion":
			iniciarSesion(request, response);
			break;
		case "/cerrar_sesion":
			cerrarSesion(request, response);
			break;
		}
	}
}
