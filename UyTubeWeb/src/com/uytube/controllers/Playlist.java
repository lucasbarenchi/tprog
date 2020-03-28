package com.uytube.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.server.implementation.DefaultPlaylistException;
import com.main.server.implementation.EntityNotFoundException;
import com.main.server.implementation.KeyAlreadyInUseException;
import com.main.server.implementation.PrivacyException;
import com.main.server.implementation.UserWebService;
import com.main.server.implementation.UserWebServiceImplService;
import com.main.server.service.PlaylistDT;
import com.main.server.service.VideoDT;
import com.main.server.implementation.PlaylistWebServiceImplService;
import com.main.server.implementation.PlaylistWebService;

/**
 * Servlet implementation class Home
 */
public class Playlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Playlist() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();
		
		String username = request.getParameter("user");
		Long playlistId = Long.valueOf(request.getParameter("id"));

		if (username != null && playlistId != null) {
			PlaylistDT playlist = null;
			try {
				playlist = userWebService.findPlaylistById(username, playlistId);
				List<VideoDT> videos = playlist.getVideos().stream()
						.filter(video -> !video.isPrivate() || username.equals(video.getUploader()))
						.collect(Collectors.toList());
				request.setAttribute("videos", videos);

			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
			request.setAttribute("playlist", playlist);
			if (request.getHeader("User-Agent").indexOf("Mobile") != -1) {
				request.getRequestDispatcher("/WEB-INF/mobile/playlistInfo.jsp").include(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/playlist/playlistInfo.jsp").include(request, response);
			}
		} else {
			request.setAttribute("titulo", "Playlist no enontrada");
			request.setAttribute("msg", "Playlist no enontrada");
			request.getRequestDispatcher("/WEB-INF/templates/error.jsp").include(request, response);
		}
	}

	private void addToPlaylist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PlaylistWebService playlistWebService = new PlaylistWebServiceImplService().getPlaylistWebServiceImplPort();
		String nickname = (String) request.getSession().getAttribute("usuario_logueado");
		Long playlistId = Long.valueOf(request.getParameter("playlist_id"));
		Long videoId = Long.valueOf(request.getParameter("video_id"));
		try {
			playlistWebService.addVideoToPlaylist(videoId, playlistId, nickname);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		boolean comesFromVideos = Boolean.parseBoolean((String) request.getParameter("comes_from_videos"));
		if(comesFromVideos) {
			response.sendRedirect(request.getContextPath() + "/videos");
		} else {
			response.sendRedirect(request.getContextPath() + "/video?id=" + videoId.toString());			
		}
		
	}

	private void createPlaylist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PlaylistWebService playlistWebService = new PlaylistWebServiceImplService().getPlaylistWebServiceImplPort();

		String nickname = request.getParameter("user");
		String name = (String) request.getParameter("playlist_name");
		boolean isPrivate = "on".equals(request.getParameter("private_checkbox")) ? true : false;
		try {
			playlistWebService.createParticularPlaylist(name, nickname, isPrivate);
			request.setAttribute("titulo", "¡Éxito! :)");
			request.setAttribute("msg", String.format("La playlist %s fue creada con éxito.", name));
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").include(request, response);
		} catch (KeyAlreadyInUseException e) {

			request.setAttribute("already_exists", "show");
			request.setAttribute("name_in_use", name);
			request.getRequestDispatcher("/WEB-INF/home/iniciar.jsp").include(request, response);
			e.printStackTrace();
		} catch (EntityNotFoundException e) {
			request.setAttribute("titulo", "Error al crear lista de reproducción :(.");
			request.setAttribute("msg", String.format("El usuario %s no existe", nickname));
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").include(request, response);
		} catch (PrivacyException e) {

		}

	}

	private void deleteFromPlaylist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usuarioLogueado = (String) request.getSession().getAttribute("usuario_logueado");

		UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();
		PlaylistWebService playlistWebService = new PlaylistWebServiceImplService().getPlaylistWebServiceImplPort();

		Long playlistId = Long.valueOf(request.getParameter("id"));
		Long video = Long.valueOf(request.getParameter("video"));

		try {
			PlaylistDT playlist = userWebService.findPlaylistById(usuarioLogueado, playlistId);
			playlistWebService.removeFromPlaylist(usuarioLogueado, playlist.getName(), video);

			response.sendRedirect(
					request.getContextPath() + String.format("/playlist?user=%s&id=%s", usuarioLogueado, playlistId));
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	private void makePrivate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String usuarioLogueado = (String) request.getSession().getAttribute("usuario_logueado");

		UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();

		PlaylistWebService playlistWebService = new PlaylistWebServiceImplService().getPlaylistWebServiceImplPort();

		Long playlistId = Long.valueOf(request.getParameter("id"));
		try {
			PlaylistDT playlist = userWebService.findPlaylistById(usuarioLogueado, playlistId);
			playlistWebService.modifyPlaylist(usuarioLogueado, playlist.getName(), true);

			response.sendRedirect(
					request.getContextPath() + String.format("/playlist?user=%s&id=%s", usuarioLogueado, playlistId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void makePublic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String usuarioLogueado = (String) request.getSession().getAttribute("usuario_logueado");

		UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();
		PlaylistWebService playlistWebService = new PlaylistWebServiceImplService().getPlaylistWebServiceImplPort();

		Long playlistId = Long.valueOf(request.getParameter("id"));
		try {
			PlaylistDT playlist = userWebService.findPlaylistById(usuarioLogueado, playlistId);
			playlistWebService.modifyPlaylist(usuarioLogueado, playlist.getName(), false);

			response.sendRedirect(
					request.getContextPath() + String.format("/playlist?user=%s&id=%s", usuarioLogueado, playlistId));
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		} catch (DefaultPlaylistException e) {
			request.setAttribute("titulo", "Error al modificar playlist");
			request.setAttribute("msg", "Una playlist por defecto no puede ser pública");
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").include(request, response);
		} catch (PrivacyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		switch (request.getRequestURI()) {
		case "/playlist":
			createPlaylist(request, response);
			break;
		case "/add_to_playlist":
			addToPlaylist(request, response);
			break;
		case "/delete_from_playlist":
			deleteFromPlaylist(request, response);
			break;
		case "/makePlaylistPrivate":
			makePrivate(request, response);
			break;
		case "/makePlaylistPublic":
			makePublic(request, response);
			break;
		}

	}

}
