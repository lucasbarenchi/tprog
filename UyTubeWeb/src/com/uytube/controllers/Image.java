package com.uytube.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.server.implementation.EntityNotFoundException;
import com.main.server.implementation.IOException;
import com.main.server.implementation.UserWebService;
import com.main.server.implementation.UserWebServiceImplService;

public class Image extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public Image() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
		UserWebServiceImplService userService = new UserWebServiceImplService();
		UserWebService userWebService = userService.getUserWebServiceImplPort();
		
		Map<String, Object> avatarData = new HashMap<>();
		try {	
			String nickname = request.getParameter("nickname");
			userWebService.getAvatarData(nickname).getMapInfo().getEntry()
				.forEach(entry -> avatarData.put(entry.getKey(), entry.getValue()));
			response.setContentType((String) avatarData.get("type"));
			response.getOutputStream().write((byte[]) avatarData.get("bytes"));
		} catch (EntityNotFoundException e) {
			
			try {
				userWebService.getDefAvatarData().getMapInfo().getEntry()
					.forEach(entry -> avatarData.put(entry.getKey(), entry.getValue()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			response.setContentType((String) avatarData.get("type"));
			response.getOutputStream().write((byte[]) avatarData.get("bytes"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
