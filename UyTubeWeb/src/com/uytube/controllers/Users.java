package com.uytube.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
import com.main.server.service.ChannelDT;
import com.main.server.service.PlaylistDT;
import com.main.server.service.UserDT;
import com.main.server.service.VideoDT;
import com.uytube.model.EstadoSesion;

@MultipartConfig
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Users() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		switch (request.getRequestURI()) {
		
			case "/create_user":
				CategoryWebService categoryWebService = new CategoryWebServiceImplService().getCategoryWebServiceImplPort();
				List<CategoryDT> categories = categoryWebService.listCategories().getItem();
				request.setAttribute("categories", categories);
				request.getRequestDispatcher("/WEB-INF/user/createUser.jsp").forward(request, response);
				break;
				
			case "/users":
				listUsers(request, response);
				break;
				
			case "/modify_user":
				modifyUser(request, response);
				break;
			case "/check_user":
				checkUser(request, response);
				break;
			case "/delete_user":
				deleteUser(request, response);
				break;
		}
		
		
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();
			String userLog = (String) request.getSession().getAttribute("usuario_logueado");
			String userToDelete = (String) request.getParameter("id");
			
			if (userLog.equals(userToDelete)) {
				userWebService.deleteUser(userToDelete);
				// Sacar la sesion
				HttpSession objSesion = request.getSession();
				objSesion.setAttribute("usuario_logueado", null);
				objSesion.setAttribute("user_email", null);
				objSesion.setAttribute("user_name", null);
				objSesion.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
				
				request.setAttribute("titulo", "¡Éxito!");
				request.setAttribute("msg", "El usuario ha sido eliminado satisfactoriamente");
				request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").forward(request, response);
			} else {
				request.setAttribute("titulo", "Error");
				request.setAttribute("msg", "Ese usuario no puede ser eliminado");
				request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("titulo", "Error");
			request.setAttribute("msg", "El usuario no se encuentra");
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").forward(request, response);
		}
	}
	
	private void checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();
		List<UserDT> users = userWebService.listUsers().getUsers();
		String nickname = request.getParameter("nickname");
		boolean isUsed = users.stream().anyMatch(u -> u.getNickname().equals(nickname));
		
		PrintWriter writer = response.getWriter();
		writer.println(String.valueOf(isUsed));
	}
	
	private void modifyUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		CategoryWebService categoryWebService = new CategoryWebServiceImplService().getCategoryWebServiceImplPort();
		UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();
		
		PlaylistWebServiceImplService playlistService = new PlaylistWebServiceImplService();
		PlaylistWebService playlistWebService = playlistService.getPlaylistWebServiceImplPort();
		
		List<CategoryDT> categories = categoryWebService.listCategories().getItem();
		
		UserDT userDt = null;
		try {
			userDt = userWebService.getUserData(request.getParameter("id"));
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		
		List<PlaylistDT> playlists = playlistWebService.listPlaylistByUser(request.getParameter("id")).getPlaylists();
		request.setAttribute("playlists", playlists);
		request.setAttribute("categories", categories);
		request.setAttribute("user", userDt);
		request.getRequestDispatcher("/WEB-INF/user/modifyUser.jsp").forward(request, response);
	}
	
	private void followUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();		
		
		try {
			userWebService.createFollowRelationship((String)request.getSession().getAttribute("usuario_logueado"), request.getParameter("nickname"));
		} catch (EntityNotFoundException e) {
			request.setAttribute("titulo", "Error");
			request.setAttribute("msg", "El usuario no se encuentra");
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").forward(request, response);
		}
		response.sendRedirect(request.getContextPath() + "/users?id=" + request.getParameter("nickname"));
	}
	
	private void unfollowUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();
		
		try {
			userWebService.unfollowUser((String)request.getSession().getAttribute("usuario_logueado"), request.getParameter("nickname"));
		} catch (EntityNotFoundException e) {
			request.setAttribute("titulo", "Error");
			request.setAttribute("msg", "El usuario no se encuentra");
			request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").forward(request, response);
		}
		response.sendRedirect(request.getContextPath() + "/users?id=" + request.getParameter("nickname"));

	}
	
	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();
		VideoWebService videoWebService = new VideoWebServiceImplService().getVideoWebServiceImplPort();
		PlaylistWebService playlistWebService = new PlaylistWebServiceImplService().getPlaylistWebServiceImplPort();
		
		if (request.getParameter("id") == null){
			List<UserDT> users = userWebService.listUsers().getUsers();
			request.setAttribute("users", users);
			request.getRequestDispatcher("/WEB-INF/user/listUsers.jsp").forward(request, response);
		} else {
			String nickname = request.getParameter("id");
			try {
				
				UserDT user = userWebService.getUserData(nickname);
				String userLog = (String) request.getSession().getAttribute("usuario_logueado");
				List<VideoDT> videos;
				List<PlaylistDT> playlists;
				if (userLog != null && user.getNickname().equals(userLog)) {
					videos = videoWebService.listVideosByUser(nickname).getVideos();
					playlists = playlistWebService.listPlaylistByUser(nickname).getPlaylists();
				} else {
					videos = videoWebService.listVideosByUser(nickname).getVideos()
							.stream()
							.filter(vid -> vid.isPrivate() == false)
							.collect(Collectors.toList());
					
					playlists = playlistWebService.listPlaylistByUser(nickname).getPlaylists()
							.stream()
							.filter(pl -> pl.isPrivate() == false)
							.collect(Collectors.toList());
				}
				
				List<UserDT> followeds = user.getFollowed()
						.stream()
						.map(us -> {
							try {
								return userWebService.getUserData(us);
							} catch (EntityNotFoundException e) {
								return null;
							}
						})
						.filter(Objects::nonNull)
						.collect(Collectors.toList());
				
				List<UserDT> followers = user.getFollowers()
						.stream()
						.map(us -> {
							try {
								return userWebService.getUserData(us);
							} catch (EntityNotFoundException e) {
								return null;
							}
						})
						.filter(Objects::nonNull)
						.collect(Collectors.toList());
				
				request.setAttribute("user", user);
				request.setAttribute("videos", videos);
				request.setAttribute("playlists", playlists);
				request.setAttribute("followeds", followeds);
				request.setAttribute("followers", followers);
				
				request.getRequestDispatcher("/WEB-INF/user/profile.jsp").forward(request, response);
			} catch (EntityNotFoundException e) {
				request.setAttribute("titulo", "Error");
				request.setAttribute("msg", "El usuario no se encuentra");
				request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		switch (request.getRequestURI()) {
		case "/modify_user":
			try {
				if (modificarUsuario(request)) {
					response.sendRedirect(request.getContextPath() + "/users?id=" + request.getParameter("id"));
				} else {
					request.setAttribute("titulo", "Ha ocurrido un error");
					request.setAttribute("msg", "Error al modificar el usuario. Intentelo nuevamente.");
					request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").include(request, response);
				}
			} catch (EntityNotFoundException e) {
				System.out.println("Categoría o usuario no encontrado");
			}
			
			break;
		case "/create_user":
			try {
				if (crearUsuario(request)) {
					request.getRequestDispatcher("/WEB-INF/user/userCreatedAndOk.jsp").include(request, response);
				} else {
					request.setAttribute("titulo", "Ha ocurrido un error");
					request.setAttribute("msg", "El nickname o el mail ya se encuentran en uso.");
					request.getRequestDispatcher("/WEB-INF/templates/infoScreen.jsp").include(request, response);
				}
			} catch (EntityNotFoundException e) {
				System.out.println("Categoría o usuario no encontrado");
			}
			
			break;	
		case "/follow_user":
			followUser(request, response);
			break;
		case "/unfollow_user":
			unfollowUser(request, response);
			break;
		}
	}
	
	private boolean modificarUsuario(HttpServletRequest request) throws EntityNotFoundException {
		try {
			request.setCharacterEncoding("UTF-8");
			UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();
			
			String nick = request.getParameter("id");
			String contrasenia = request.getParameter("contrasenia");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			
			String fechaNac = request.getParameter("fechaNacimiento");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate birthDate = LocalDate.parse(fechaNac, formatter);
			
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String finalDate = birthDate.format(formatter2);
			
			String nombreCanal = request.getParameter("nombreCanal");
			if (nombreCanal.isEmpty()) {
				nombreCanal = nick;
			}
			String descripcion = request.getParameter("descripcion");
			String category = request.getParameter("category");
			byte[] imageBytes;
			
			Part imagePart = request.getPart("image"); 
			String name = imagePart.getSubmittedFileName();
			if (name != "") {
				InputStream imageStream = imagePart.getInputStream();
			    imageBytes = new byte[imageStream.available()];
			    imageStream.read(imageBytes);
			} else {
				imageBytes = null;
			}
			
			boolean esPrivado = request.getParameter("esPrivado") != null;
			
			UserDT userDT = new UserDT();
			userDT.setNickname(nick);
			userDT.setName(nombre);
			userDT.setSurname(apellido);
			userDT.setBirthdate(finalDate);
			userDT.setAvatar(imageBytes);
			ChannelDT channelDT = new ChannelDT();
			channelDT.setDescription(descripcion);
			channelDT.setName(nombreCanal);
			channelDT.setPrivateChannel(esPrivado);
			userDT.setChannel(channelDT);
			
			userWebService.modifyUser(userDT);
			if (contrasenia != null && !contrasenia.isEmpty()) {
				userWebService.setPassword(nick, contrasenia);
			}
			if (!category.equals("--")) {
				userWebService.setCategoryToChannel(nick, category);
			} else {
				userWebService.setCategoryToChannel(nick, null);
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean crearUsuario(HttpServletRequest request) throws EntityNotFoundException {
		try {
			request.setCharacterEncoding("UTF-8");
			UserWebService userWebService = new UserWebServiceImplService().getUserWebServiceImplPort();
			
			String nick = request.getParameter("nickname");
			String email = request.getParameter("email");
			String password = request.getParameter("contrasenia");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			
			String fechaNac = request.getParameter("fechaNacimiento");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate birthDate = LocalDate.parse(fechaNac, formatter);
			
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String finalDate = birthDate.format(formatter2);
			
			
			
			String nombreCanal = request.getParameter("nombreCanal");
			if (nombreCanal.isEmpty()) {
				nombreCanal = nick;
			}
			String descripcion = request.getParameter("descripcion");
			String category = request.getParameter("category");
			byte[] imageBytes;
			
			Part imagePart = request.getPart("image"); 
			String name = imagePart.getSubmittedFileName();
			if (name != "") {
				InputStream imageStream = imagePart.getInputStream();
			    imageBytes = new byte[imageStream.available()];
			    imageStream.read(imageBytes);
			} else {
				Map<String, Object> defAvatar = new HashMap<>();
				userWebService.getDefAvatarData().getMapInfo().getEntry()
					.forEach(entry -> defAvatar.put(entry.getKey(), entry.getValue()));
				imageBytes = (byte[]) defAvatar.get("bytes");
			}
			
			boolean esPrivado = request.getParameter("esPrivado") != null;

			if (userWebService.createUser(nick, nombre, apellido, email, finalDate, imageBytes, descripcion, nombreCanal, esPrivado)) {
				userWebService.setPassword(nick, password);
				if (!category.equals("--")) {
					userWebService.setCategoryToChannel(nick, category);
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
