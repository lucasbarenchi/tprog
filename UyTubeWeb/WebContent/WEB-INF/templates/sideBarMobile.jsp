<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.main.logic.controller.ControllerFactory, com.main.logic.interfaces.IPlaylistController,
	com.main.logic.interfaces.ICategoryController, com.main.logic.dts.PlaylistDT, com.main.logic.dts.CategoryDT" %>

        <!-- Sidebar  -->
        <nav id="sidebar" >
            <div class="sidebar-header">
                <a style="font-size:35px" href="/home">UyTube</a>
            </div>

            <ul class="list-unstyled components">
                
                <li>
                
                	<a href="#playlistsSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Listas de reproducción</a>
                    <ul class="collapse list-unstyled" id="playlistsSubmenu">
                    <% IPlaylistController playlistController = ControllerFactory.getInstance().getPlaylistController();%>
                    <% if (request.getSession().getAttribute("usuario_logueado") != null){%>
                    <li>
                    	<h6 class="dropdown-header">Tus listas de reproducción</h6>
                    </li>
                    	<%	List<PlaylistDT> playlists = playlistController.listPlaylistByUser((String)request.getSession().getAttribute("usuario_logueado"));%>
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
       	 				   	 					List<PlaylistDT> publicPlaylistsNotFromUser = playlistController.listPublicPlaylistsNotFromUser((String)request.getSession().getAttribute("usuario_logueado"));
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
                  
                <li>
                    <a href="/videos">Ver videos</a>
                </li>
                <li>
                    <a href="/cerrar_sesion">Salir</a>
                </li>
            </ul>

        </nav>
       