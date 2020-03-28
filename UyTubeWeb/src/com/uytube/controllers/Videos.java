package com.uytube.controllers;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.server.implementation.CategoryWebService;
import com.main.server.implementation.CategoryWebServiceImplService;
import com.main.server.implementation.EntityNotFoundException;
import com.main.server.implementation.PlaylistWebService;
import com.main.server.implementation.PlaylistWebServiceImplService;
import com.main.server.implementation.UserWebService;
import com.main.server.implementation.UserWebServiceImplService;
import com.main.server.implementation.VideoWebService;
import com.main.server.implementation.VideoWebServiceImplService;
import com.main.server.service.CategoryDT;
import com.main.server.service.CommentDT;
import com.main.server.service.PlaylistDT;
import com.main.server.service.RatingEnum;
import com.main.server.service.VideoDT;

/**
 * Servlet implementation class Home
 */
public class Videos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Videos() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void videoInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		VideoWebService videoWebService = new VideoWebServiceImplService().getVideoWebServiceImplPort();
		PlaylistWebService playlistWebService = new PlaylistWebServiceImplService().getPlaylistWebServiceImplPort();
    	
    	String nickname = String.valueOf(request.getSession().getAttribute("usuario_logueado"));
    	Long id = Long.valueOf(request.getParameter("id"));
    	
    	if(request.getParameter("reloaded") == null && nickname != null) {
    		UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();
        	try {
        		userWebService.addNewVisit(nickname, id);
        	} catch (Exception e) {}
    	}
    	
		VideoDT videoDt = null; 
		CommentDT comments = null;
		try {
			videoDt = videoWebService.getVideo(id);
			comments = videoWebService.listComments(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		List<PlaylistDT> playlists = playlistWebService.listPlaylistByUser(nickname).getPlaylists();
		
		request.setAttribute("playlists", playlists);
		request.setAttribute("comments", comments);
		request.setAttribute("video", videoDt);
		if (request.getHeader("User-Agent").indexOf("Mobile") != -1) {
			request.getRequestDispatcher("/WEB-INF/mobile/videoInfo.jsp").include(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/video/videoInfo.jsp").include(request, response);
		}
    }
    
    private void commentVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	VideoWebService videoWebService = new VideoWebServiceImplService().getVideoWebServiceImplPort();

		Long videoId = Long.valueOf(request.getParameter("id"));
		String nickname = String.valueOf(request.getSession().getAttribute("usuario_logueado"));
    	String text = (String) request.getParameter("text");
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		try {
			videoWebService.commentVideo(nickname, text, LocalDateTime.now().format(formatter), videoId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		response.sendRedirect(request.getContextPath() + "/video?id=" + videoId.toString() + "&reloaded=true");
    }
    
    private void commentComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	VideoWebService videoWebService = new VideoWebServiceImplService().getVideoWebServiceImplPort();
    	
    	Long videoId = Long.valueOf(request.getParameter("id"));
    	Long commentId = Long.valueOf(request.getParameter("comment_id"));
    	String nickname = String.valueOf(request.getSession().getAttribute("usuario_logueado"));
    	String text = (String) request.getParameter("text");
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		try {
			videoWebService.commentComment(nickname, text, LocalDateTime.now().format(formatter), videoId, commentId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		response.sendRedirect(request.getContextPath() + "/video?id=" + videoId.toString() + "&reloaded=true");
    }
    
    private void likeVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	VideoWebService videoWebService = new VideoWebServiceImplService().getVideoWebServiceImplPort();
    	
		Long videoId = Long.valueOf(request.getParameter("id"));
		String nickname = String.valueOf(request.getSession().getAttribute("usuario_logueado"));
    	try {
    		String alreadyRated = request.getParameter("already_rated");
    		
    		if("true".equals(alreadyRated)) {
    			videoWebService.deleteRaiting(videoId, nickname);
    		} else {
    			videoWebService.rateVideo(videoId, nickname, RatingEnum.LIKE);
    		}
    	} catch (Exception e) {
    		System.out.println("Error");
    	}
    	
    	response.sendRedirect(request.getContextPath() + "/video?id=" + videoId.toString() + "&reloaded=true");
    }
    
    private void dislikeVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	VideoWebService videoWebService = new VideoWebServiceImplService().getVideoWebServiceImplPort();
    	
    	Long videoId = Long.valueOf(request.getParameter("id"));
    	String nickname = String.valueOf(request.getSession().getAttribute("usuario_logueado"));
    	try {
    		String alreadyRated = request.getParameter("already_rated");
    		
    		if("true".equals(alreadyRated)) {
    			videoWebService.deleteRaiting(videoId, nickname);
    		} else {
    			videoWebService.rateVideo(videoId, nickname, RatingEnum.DISLIKE);
    		}
    	} catch (Exception e) {
    		System.out.println("Error");
    	}
    	
    	response.sendRedirect(request.getContextPath() + "/video?id=" + videoId.toString() + "&reloaded=true");
    }
    
    private void listVideos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	VideoWebService videoWebService = new VideoWebServiceImplService().getVideoWebServiceImplPort();
    	PlaylistWebService playlistWebService = new PlaylistWebServiceImplService().getPlaylistWebServiceImplPort();

    	String nickname = String.valueOf(request.getSession().getAttribute("usuario_logueado"));
    	
    	List<PlaylistDT> playlists = playlistWebService.listPlaylistByUser(nickname).getPlaylists();
		request.setAttribute("playlists", playlists);
    	List<VideoDT> userVideoDTs = new ArrayList<VideoDT>();
    	try {
    		userVideoDTs = videoWebService.listVideosByUser(nickname).getVideos();
    	} catch (Exception e) {
    	}
		List<VideoDT> publicVideos = videoWebService.listVideosNotFromUser(nickname).getVideos();
		userVideoDTs.addAll(publicVideos);
		
		request.setAttribute("videos", userVideoDTs);
		if (request.getHeader("User-Agent").indexOf("Mobile") != -1) {
			request.getRequestDispatcher("/WEB-INF/mobile/listVideos.jsp").include(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/video/listVideos.jsp").include(request, response);
		}
    }
    
    private void createVideoForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	CategoryWebService categoryWebService = new CategoryWebServiceImplService().getCategoryWebServiceImplPort();
    	
		List<CategoryDT> categories = categoryWebService.listCategories().getItem();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/WEB-INF/video/altaVideo.jsp").include(request, response);
    }
    
    private void createVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	VideoWebService videoWebService = new VideoWebServiceImplService().getVideoWebServiceImplPort();
		
		String owner = request.getSession().getAttribute("usuario_logueado").toString();
		String title = (String) request.getParameter("video_name");
		int length = Integer.parseInt(request.getParameter("length"));
		String url = (String) request.getParameter("url");
		String description = (String) request.getParameter("description");
		String category = (String) request.getParameter("category");
		
		String upload_date = (String) request.getParameter("upload_date");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate uploadDate = LocalDate.parse(upload_date, formatter);
		
		DateTimeFormatter finalFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			if (category.equals("-")) {
				// SIN CATEGORIA
				videoWebService.createVideo(owner, title, length, url, description, uploadDate.format(finalFormatter));
			} else {
				// CON CATEGORIA
				videoWebService.createVideoWithCategory(owner, title, length, url, description, uploadDate.format(finalFormatter), category);
			}
			request.setAttribute("titulo", "¡Salió todo bien! :)");
			request.setAttribute("msg", String.format("El video %s fue creada con éxito.", title));
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").include(request, response);
		} catch (EntityNotFoundException e) {
			request.setAttribute("titulo", "Error al publicar video :(.");
			request.setAttribute("msg", String.format("El usuario %s no existe", owner));
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").include(request, response);
		} catch (Exception e) {
			request.setAttribute("titulo", "Error al publicar video :(.");
			request.setAttribute("msg", String.format("Error inesperado al crear el video %s", title));
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").include(request, response);
		}
    }
    
    private void modifyVideoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		VideoWebService videoWebService = new VideoWebServiceImplService().getVideoWebServiceImplPort();
    	CategoryWebService categoryWebService = new CategoryWebServiceImplService().getCategoryWebServiceImplPort();

    	Long id = Long.valueOf(request.getParameter("id"));
    	try {
			VideoDT video = videoWebService.getVideo(id);
			request.setAttribute("video", video);
			
			List<CategoryDT> categories = categoryWebService.listCategories().getItem();
			request.setAttribute("categories", categories);
			
	    	request.getRequestDispatcher("/WEB-INF/video/modifyVideo.jsp").include(request, response);
	    	
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    private void modifyVideoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	VideoWebServiceImplService videoService = new VideoWebServiceImplService();
		VideoWebService videoWebService = videoService.getVideoWebServiceImplPort();
    	CategoryWebService categoryWebService = new CategoryWebServiceImplService().getCategoryWebServiceImplPort();
    	
    
    	Long videoId = Long.valueOf(request.getParameter("id"));
    	VideoDT updatedVideo = new VideoDT();
    	
		String title = (String) request.getParameter("video_name");
		int length = Integer.parseInt(request.getParameter("length"));
		String url = (String) request.getParameter("url");
		String description = (String) request.getParameter("description");
		
		String category = (String) request.getParameter("category");
    	CategoryDT categoryDT;
		
		String upload_date = (String) request.getParameter("upload_date");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate uploadDate = LocalDate.parse(upload_date, formatter);
		
		boolean isPrivate;
		String privacy = request.getParameter("privacy");
		isPrivate = "public".equals(privacy) ? false : true; 
    	
		updatedVideo.setTitle(title);
		updatedVideo.setLength(length);
		updatedVideo.setUrl(url);
		updatedVideo.setDescription(description);
		updatedVideo.setUploadDate(uploadDate.toString());
		updatedVideo.setPrivate(isPrivate);
		
		try {
			categoryDT = categoryWebService.getCategory(category);
			CategoryDT actualCategory = new CategoryDT();
			actualCategory.setName(categoryDT.getName());
			updatedVideo.setCategory(actualCategory);
		} catch (EntityNotFoundException e) {
			//No debería pasar
			request.setAttribute("titulo", "Error al publicar video :(.");
			request.setAttribute("msg", String.format("La categoría %s no existe", category));
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").include(request, response);
		}
		
		try {
			videoWebService.modifyVideo(updatedVideo, videoId);
			response.sendRedirect(String.format("/video?id=%s", videoId.toString()));
			
		} catch (EntityNotFoundException e) {
			request.setAttribute("titulo", "Error al publicar video :(.");
			request.setAttribute("msg", String.format("El video con id '%s' no existe", videoId));
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").include(request, response);
		} catch (Exception e) {
			request.setAttribute("titulo", "Error al publicar video :(.");
			request.setAttribute("msg", String.format("Error inesperado al crear el video %s", title));
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").include(request, response);
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		switch (request.getRequestURI()) {
			case "/create_video":
				createVideoForm(request, response);
				break;
			case "/videos":
				listVideos(request, response);
				break;
			case "/video":
				videoInfo(request, response);
				break;
			case "/modify_video":
				modifyVideoGet(request, response);
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		switch (request.getRequestURI()) {
			case "/create_video":
				createVideo(request, response);
				break;
			case "/comment_video":
				commentVideo(request, response);
				break;
			case "/comment_comment":
				commentComment(request, response);
				break;
			case "/like_video":
				likeVideo(request, response);
				break;
			case "/dislike_video":
				dislikeVideo(request, response);
				break;
			case "/modify_video":
				modifyVideoPost(request, response);
				break;
		}
	}

}
