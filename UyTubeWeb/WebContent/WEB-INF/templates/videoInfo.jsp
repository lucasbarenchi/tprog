<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.main.server.service.VideoDT, com.main.server.service.PlaylistDT, com.uytube.utils.UrlUtils" %>
<% String usuarioLogueado = (String) request.getSession().getAttribute("usuario_logueado"); %>
<% List<VideoDT> videos = (List<VideoDT>) request.getAttribute("videos"); %>
<% PlaylistDT playlist = (PlaylistDT) request.getAttribute("playlist"); %>

<% for(VideoDT videoDt : videos) { %>
	<div class="card mt-3">
      <div class="row">
          <div class="col-md-3 imgContainer px-2 mt-2 mb-2">
         		<a href="/video?id=<%=videoDt.getVideoId().toString()%>">
         			<img src="https://img.youtube.com/vi/<%=UrlUtils.getVideoUrl(videoDt.getUrl())%>/hqdefault.jpg" id="videoImg" class="w-100">
         		</a>
         </div>
          <div class="col-md-9 px-3">
            <div class="card-block py-2 d-flex flex-column">
            <div> 
              <a class="card-title pr-2 align-middle" id="videoTitle" href="/video?id=<%=videoDt.getVideoId().toString()%>"><%=videoDt.getTitle()%></a>
				<%
					if (videoDt.isPrivate()) {
				%>
					<span class="badge badge-secondary px-1 py-1"><i class="fas fa-lock"></i>  PRIVADO</span>
				<%
					}
				%>	 
				<%
	 					if ((playlist != null && playlist.getOwner().getOwner().equals(usuarioLogueado)) || (videoDt.getUploader().equals(usuarioLogueado))) {
	 				%>
					<button type="button" class="btn btn-link btn-sm text-white float-right pt-1 rounded-0" id="more_options" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="fas fa-ellipsis-v"></i>
					</button>
					<div class="dropdown-menu dropdown-menu-right rounded-0 bg-light text-center" aria-labelledby="more_options">
						<%
							if(playlist != null && playlist.getOwner().getOwner().equals(usuarioLogueado)) {
						%>
				    	<form class="dropdown-item text-dark bg-light" id="remove_form" action="delete_from_playlist?id=<%=playlist.getPlaylistId()%>&video=<%=videoDt.getVideoId()%>" method="POST">
							<input type="button" value="Quitar de la lista" class="btn btn-link text-dark" onclick="submit()">
				    	</form>
						<%
							}
						%> 
				    	<%
 				    		if(videoDt.getUploader().equals(usuarioLogueado)) {
 				    	%>
					    <a href="/modify_video?id=<%=videoDt.getVideoId()%>" class="dropdown-item btn btn-link text-dark">Editar</a>
			    		<% } %>
			    	</div>
		    	<% } %>
	                      	
	          	</div>
              	<p class="card-text pr-4" id="videoDescription"><%= videoDt.getDescription() %></p>
              	<p class="card-text pr-4 mt-auto"><b>Subido por:</b> <%= videoDt.getUploader() %></p>
            </div>
          </div>

        </div>
      </div>
 <% } %>