<%@ page import="java.util.List, com.main.server.service.PlaylistDT" %>
<% String usuarioLogueado = (String) request.getSession().getAttribute("usuario_logueado"); %>
<% List<PlaylistDT> playlists = (List<PlaylistDT>) request.getAttribute("playlists"); %>
<% for(PlaylistDT playlist : playlists) { %>
	<div class="card mt-3">
      <div class="row">
          <div class="col-md-3 imgContainer px-2 mt-2 mb-2">
         		<a href="/playlist?user=<%=playlist.getOwner().getOwner()%>&id=<%=playlist.getPlaylistId().toString()%>">
         			<img src="/media/img/youtube.png" id="videoImg" class="w-100">
         		</a>
         </div>
          <div class="col-md-8 mr-3">
            <div class="card-block py-2 d-flex flex-column">
            <div> 
              <a class="card-title align-middle" id="videoTitle" href="/playlist?user=<%=playlist.getOwner().getOwner()%>&id=<%=playlist.getPlaylistId().toString()%>"><%=playlist.getName()%></a>
				<% if (playlist.isPrivate()) { %>
					<span class="badge badge-secondary px-1 py-1"><i class="fas fa-lock"></i>PRIVADO</span>
				<% } %>	 	                      	
	        </div>
              	<p class="card-text" id="videoDescription"><%= playlist.getOwner().getName() %></p>
              	<p class="card-text mt-auto"><b>Cantidad de videos:</b> <%= playlist.getVideos().size() %></p>
            </div>
          </div>

        </div>
      </div>
 <% } %>