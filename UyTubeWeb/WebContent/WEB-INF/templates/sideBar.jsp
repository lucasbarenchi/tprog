<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.main.server.service.PlaylistDT, com.main.server.service.CategoryDT,
	com.main.server.implementation.PlaylistWebServiceImplService, com.main.server.implementation.PlaylistWebService,
	com.main.server.implementation.CategoryWebServiceImplService, com.main.server.implementation.CategoryWebService,
	com.main.server.service.CategoryDTArray" %>

        <!-- Sidebar  -->
        <nav id="sidebar" >
            <div class="sidebar-header">
                <a style="font-size:35px" href="/home">UyTube</a>
            </div>

            <ul class="list-unstyled components">
                <% if (request.getSession().getAttribute("usuario_logueado") != null){%>
                	<li>
                    	<a href="/create_video">Subir video</a>
                	</li>
                <%}%>
                
                <li>
                
                	<a href="#playlistsSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Listas de reproducción</a>
                    <ul class="collapse list-unstyled" id="playlistsSubmenu">
                    <% PlaylistWebService playlistWebService = new PlaylistWebServiceImplService().getPlaylistWebServiceImplPort();%>
                    <% if (request.getSession().getAttribute("usuario_logueado") != null){%>
                    <li>
                    	<h6 class="dropdown-header">Tus listas de reproducción</h6>
                    </li>
                        <li>
                            <a href="/history"> Historial</a>
                        </li>
                    	<%	List<PlaylistDT> playlists = playlistWebService.listPlaylistByUser((String)request.getSession().getAttribute("usuario_logueado")).getPlaylists();%>

                    	<%	for(PlaylistDT pl : playlists){ %>
                    			<li>
                            		<a href="/playlist?user=<%= request.getSession().getAttribute("usuario_logueado") %>&id=<%=pl.getPlaylistId()%>"> <%=pl.getName()%></a>
                        		</li>
                    		<%
                    			}
                    		%>                   	
	                <%
                   		                	}
                   		                %>
	                
                   	<h6 class="dropdown-header ml-0 pl-3">Listas de reproducción de otros</h6>
       	 				
   	 				<%
   	 					String usuarioLogueado = request.getSession().getAttribute("usuario_logueado") != null ? (String)request.getSession().getAttribute("usuario_logueado") : "";
       	 				List<PlaylistDT> publicPlaylistsNotFromUser = playlistWebService.listPlaylistNotFromUser(usuarioLogueado).getPlaylists();
       	 			%>
                   	<%
                   		for(PlaylistDT pl : publicPlaylistsNotFromUser){
                   	%>
              			<li>
                      		<a href="/playlist?user=<%=pl.getOwner().getOwner()%>&id=<%=pl.getPlaylistId()%>"> <%= pl.getName() %></a>
                  		</li>
                   	<% } %>
                   	
                    	
                    </ul>
                </li>
                <% if (request.getSession().getAttribute("usuario_logueado") != null){%>
                	<% String alreadyExists = (String) request.getAttribute("already_exists"); %>
	                <% String name = (String) request.getAttribute("name_in_use"); %>
                	<li>
	                    <a href="#createPlaylistSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Crear playlist</a>
	                    <ul class="collapse list-unstyled <%= alreadyExists %>" id="createPlaylistSubmenu">
	                    	<li>
								<div class="pt-2 pb-2">
									<div class="col-auto mx-auto text-center">
										<form action="playlist?user=<%= request.getSession().getAttribute("usuario_logueado") %>" method="POST">
								  			<div class="form-group mb-1">
								    			<input type="text" class="form-control rounded-0" name="playlist_name" placeholder="Nombre"
								    			<%if(name != null) { %>
								    				value="<%= name %>">
								    				<p class="text-danger small my-0 py-0 text-left" >Nombre en uso</p>
								    			<% } else {%>
								    				>
								    			<% } %>
								  			</div>
								  			<div class="form-check">
												<input class="form-check-input" type="checkbox" name="private_checkbox" id="private_checkbox">
													<label class="form-check-label" for="private_checkbox">
														Privada
											  		</label>
											</div>
								  			<button type="submit" class="btn-sm btn-danger rounded-0 mt-1">Confirmar</button>
										</form>
									</div>
							  	</div>
	                    	</li>
                    	</ul>  
                   	</li>
                 <% } %>
                  
                <li>
                    <a href="/videos">Ver videos</a>
                </li>
                <li>
                    <a href="/users">Ver usuarios</a>
                </li>
                <li>
                    <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Categorías</a>
                    <ul class="collapse list-unstyled" id="pageSubmenu">
                    	<% CategoryWebServiceImplService categoryService = new CategoryWebServiceImplService();  %>
						<%		CategoryWebService categoryWebService = categoryService.getCategoryWebServiceImplPort(); %>
						<% String a = "HOLA"; %>
						<% List<CategoryDT> categories = categoryWebService.listCategories().getItem();  %>
                        <% for(CategoryDT category : categories) {%>
                        <li>
                            <a href="/categories?name=<%= URLEncoder.encode(category.getName(), "UTF-8")%>"> <%= category.getName() %></a>
                        </li>
                        <% } %>
                    </ul>
                </li>
            </ul>
        </nav>
       