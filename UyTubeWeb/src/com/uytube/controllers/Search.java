package com.uytube.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.server.implementation.VideoWebService;
import com.main.server.implementation.VideoWebServiceImplService;
import com.main.server.service.ChannelDT;
import com.main.server.service.PlaylistDT;
import com.main.server.service.Playlists;
import com.main.server.service.UserDT;
import com.main.server.service.VideoDT;
import com.main.server.service.Videos;
import com.main.server.implementation.PlaylistWebService;
import com.main.server.implementation.PlaylistWebServiceImplService;
import com.main.server.implementation.UserWebService;
import com.main.server.implementation.UserWebServiceImplService;

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		switch (request.getRequestURI()) {
			case "/search":
		    	String nickname = (String) request.getSession().getAttribute("usuario_logueado");
		    	boolean sinFiltros = (request.getParameter("show_videos") == null) && (request.getParameter("show_playlists") == null) && (request.getParameter("show_channels") == null);
		    	boolean orderByDate = (request.getParameter("order_by") != null) && request.getParameter("order_by").toString().equals("Fecha (desc)");
		    	
		    	// Add the videos
		    	if (sinFiltros || "videos".equals(request.getParameter("show_videos"))) {	
			    	List<VideoDT> videosOutput = getVideos(nickname, request.getParameter("search_data").toString(), orderByDate);
			    	request.setAttribute("show_videos", true);
					request.setAttribute("videos", videosOutput);
		    	}
		    	
		    	// Add the playlists
		    	if (sinFiltros || "playlists".equals(request.getParameter("show_playlists"))) {
		    		List<PlaylistDT> playlistsOutput = getPlaylists(nickname, request.getParameter("search_data").toString(), orderByDate);
					request.setAttribute("show_playlists", true);
					request.setAttribute("playlists", playlistsOutput);
		    	}
		    	
		    	// Add the channels
		    	if (sinFiltros || "channels".equals(request.getParameter("show_channels"))) {
		    		List<ChannelDT> channelsOutput = getChannel(nickname, request.getParameter("search_data").toString(), orderByDate);
					request.setAttribute("show_channels", true);
					request.setAttribute("channels", channelsOutput);
		    	}
		    	if (request.getHeader("User-Agent").indexOf("Mobile") != -1) {
					request.getRequestDispatcher("/WEB-INF/mobile/search.jsp").include(request, response);
		    	} else {
					request.getRequestDispatcher("/WEB-INF/home/search.jsp").include(request, response);
		    	}
	        break;
		}
	}
	
	private List<com.main.server.service.VideoDT> getVideos(String nickname, String searchData, boolean orderByDate){
		VideoWebServiceImplService videoService = new VideoWebServiceImplService();
		VideoWebService videoWebService = videoService.getVideoWebServiceImplPort();
    	
    	Videos array = videoWebService.listVideos();
    	List<VideoDT> videos = array.getVideos();
    	
    	if (orderByDate) {
    		// SORT BY DATE
        	List<VideoDT> videosOutputByDate = videos.stream()
        			.filter(videoDT -> videoDT.getTitle().toLowerCase().contains(searchData.toLowerCase())) // COINCIDE CON EL SEARCH
        			.filter(videoDT -> (!videoDT.isPrivate()) || (videoDT.getUploader().equals(nickname))) // ES PUBLICO O EL USUARIO ES EL DUEÑO
        			.sorted((v1, v2) -> {
        				LocalDate dateV2 = LocalDate.parse(v2.getUploadDate());
        				LocalDate dateV1 = LocalDate.parse(v1.getUploadDate());
        				
        				return dateV2.compareTo(dateV1);
        			}) // ORDEN POR UPLOADDATE -- DESC
        			.collect(Collectors.toList());
        	
        	return videosOutputByDate;
    	}
    	
    	
    	List<VideoDT> videosOutput = videos.stream()
    			.filter(videoDT -> videoDT.getTitle().toLowerCase().contains(searchData.toLowerCase())) // COINCIDE CON EL SEARCH
    			.filter(videoDT -> (!videoDT.isPrivate()) || (videoDT.getUploader().equals(nickname))) // ES PUBLICO O EL USUARIO ES EL DUEÑO
    			.sorted((v1, v2) -> v1.getTitle().compareTo(v2.getTitle())) // ORDEN ALFABETICO -- ASC
    			.collect(Collectors.toList());
    	
    	return videosOutput;
	}
	
	private List<PlaylistDT> getPlaylists(String nickname, String searchData, boolean orderByDate){
		
		UserWebServiceImplService userService = new UserWebServiceImplService();
		UserWebService userWebService = userService.getUserWebServiceImplPort();
		
		PlaylistWebServiceImplService playlistService = new PlaylistWebServiceImplService();
		PlaylistWebService playlistWebService = playlistService.getPlaylistWebServiceImplPort();
		
		List<UserDT> users = userWebService.listUsers().getUsers();
		
    	if (orderByDate) {
    		// SORT BY DATE
    		List<PlaylistDT> playlistsByDate = users.stream()
    				.map(user -> playlistWebService.listPlaylistByUser(user.getNickname()))
    				.map(Playlists::getPlaylists)
    				.filter(Objects::nonNull)
    				.flatMap(Collection::stream)
    				.filter(playlistDT -> playlistDT.getName().toLowerCase().contains(searchData.toLowerCase())) // COINCIDE CON EL SEARCH
    				.filter(playlistDT -> (!playlistDT.isPrivate()) || (playlistDT.getOwner().getOwner().equals(nickname))) // ES PUBLICA O EL USUARIO ES EL DUEÑO
    	    		.sorted((pl1, pl2) -> compareListVideos(pl2.getVideos(), pl1.getVideos())) // ORDEN POR VIDEOS UPLOADDATE -- DESC
    				.collect(Collectors.toList());
    			
    		return playlistsByDate;
    	}
    	
		List<PlaylistDT> playlists = users.stream()
			.map(user -> playlistWebService.listPlaylistByUser(user.getNickname()))
			.map(Playlists::getPlaylists)
			.filter(Objects::nonNull)
			.flatMap(Collection::stream)
			.filter(playlistDT -> playlistDT.getName().toLowerCase().contains(searchData.toLowerCase())) // COINCIDE CON EL SEARCH
			.filter(playlistDT -> (!playlistDT.isPrivate()) || (playlistDT.getOwner().getOwner().equals(nickname))) // ES PUBLICA O EL USUARIO ES EL DUEÑO
    		.sorted((pl1, pl2) -> pl1.getName().compareTo(pl2.getName())) // ORDEN ALFABETICO -- ASC
			.collect(Collectors.toList());
		
		return playlists;
	}
	
	private List<ChannelDT> getChannel(String nickname, String searchData, boolean orderByDate){
		UserWebServiceImplService userService = new UserWebServiceImplService();
		UserWebService userWebService = userService.getUserWebServiceImplPort();
		
    	if (orderByDate) {
        	List<ChannelDT> channelsOutput = userWebService.listUsers().getUsers().stream()
            		.map(UserDT::getChannel)
            		.filter(channel -> channel.getName().toLowerCase().contains(searchData.toLowerCase())) // COINCIDE CON EL SEARCH
            		.filter(channel -> (!channel.isPrivateChannel() || (channel.getOwner().equals(nickname)))) // ES PUBLICO O EL USUARIO ES EL DUEÑO
    	    		.sorted((ch1, ch2) -> compareListVideos(ch2.getVideos(), ch1.getVideos())) // ORDEN POR VIDEOS UPLOADDATE -- DESC
            		.collect(Collectors.toList());
            	
            return channelsOutput;
    	}
    	
    	List<ChannelDT> channelsOutput = userWebService.listUsers().getUsers().stream()
    		.map(UserDT::getChannel)
    		.filter(channel -> channel.getName().toLowerCase().contains(searchData.toLowerCase())) // COINCIDE CON EL SEARCH
    		.filter(channel -> (!channel.isPrivateChannel() || (channel.getOwner().equals(nickname)))) // ES PUBLICO O EL USUARIO ES EL DUEÑO
    		.sorted((ch1, ch2) -> ch1.getName().compareTo(ch2.getName())) // ORDEN ALFABETICO -- ASC
    		.collect(Collectors.toList());
    	
    	return channelsOutput;
	}
	
	private int compareListVideos(List<VideoDT> list, List<VideoDT> list2) {
		if (list.size() == 0 && list2.size() == 0) {
			return 0;
		} else if (list.size() == 0) { // LA 1 ES MENOR
			return -1;
		} else if (list2.size() == 0) { // LA 2 ES MENOR
			return 1;
		}
		
		VideoDT video1 = list.stream()
			.sorted((v1, v2) -> {
				LocalDate dateV2 = LocalDate.parse(v2.getUploadDate());
				LocalDate dateV1 = LocalDate.parse(v1.getUploadDate());
				
				return dateV2.compareTo(dateV1);
			})
			.collect(Collectors.toList()).get(0);
		
		VideoDT video2 = list2.stream()
			.sorted((v1, v2) -> {
				LocalDate dateV2 = LocalDate.parse(v2.getUploadDate());
				LocalDate dateV1 = LocalDate.parse(v1.getUploadDate());
				
				return dateV2.compareTo(dateV1);
			})
			.collect(Collectors.toList()).get(0);
		
		LocalDate dateVideo1 = LocalDate.parse(video1.getUploadDate());
		LocalDate dateVideo2 = LocalDate.parse(video2.getUploadDate());
		
		return dateVideo1.compareTo(dateVideo2);
	}

}