package com.uytube.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.server.service.PlaylistDT;
import com.main.server.service.VideoDT;
import com.main.server.implementation.EntityNotFoundException;
import com.main.server.implementation.PlaylistWebService;
import com.main.server.implementation.PlaylistWebServiceImplService;
import com.main.server.implementation.VideoWebService;
import com.main.server.implementation.VideoWebServiceImplService;

/**
 * Servlet implementation class Category
 */
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Category() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		String category = request.getParameter("name");
		VideoWebServiceImplService videoService = new VideoWebServiceImplService();
		VideoWebService videoWebService = videoService.getVideoWebServiceImplPort();
		PlaylistWebService playlistWebService = new PlaylistWebServiceImplService().getPlaylistWebServiceImplPort();
		
		List<VideoDT> videos = videoWebService.listVideosByCategory(category).getVideos().stream()
				.filter(video -> !video.isPrivate() || video.getUploader().equals(request.getSession().getAttribute("usuario_logueado")))
				.collect(Collectors.toList());
		List<PlaylistDT> playlists;
		try {
			playlists = playlistWebService.listPlaylistByCategory(category).getPlaylists();
		} catch (EntityNotFoundException e) {
			playlists = new ArrayList<>();
		}
		request.setAttribute("category", category);
		request.setAttribute("playlists", playlists);
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/WEB-INF/category/categoryInfo.jsp").include(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
