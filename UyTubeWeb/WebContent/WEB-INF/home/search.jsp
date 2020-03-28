<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.main.server.service.VideoDT, com.main.server.service.PlaylistDT, com.main.server.service.ChannelDT" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>UyTube</title>

    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="/media/bootstrapResource/bootstrap.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- Our Custom CSS -->
    <link rel="stylesheet" type=""href="/media/styles/style.css">

    <!-- Font Awesome JS -->
    <script defer src="/media/bootstrapResource/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="/media/bootstrapResource/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
</head>

<body>
    <div class="wrapper">
    
		<!--  Sidebar  -->
        <jsp:include page="/WEB-INF/templates/sideBar.jsp"/>

		<!--  Navbar  -->
        <div id="content">

            <jsp:include page="/WEB-INF/templates/navBar.jsp"/>
            
			<!--  Page content  -->
            <div id='page-content'>
            	<div class="card col-md-12 mx-1 mt-1 py-3 px-3">
            		<h2>Filtrar</h2>
					<form method="post" action="/search">
					  <div class="form-row">
					    <div class="form-group col-md-4">
					      <label for="search" style="color:white">Buscar</label>
					      <input type="text" name="search_data" class="form-control" id="inputName" value=<%=request.getParameter("search_data")%>>
					    </div>
					    <div class="form-group mx-2">
						   	<label for="category">Ordenar</label>
						    <select name="order_by" class="form-control" id="inputCategory">
						      	<option> Alfabéticamente (asc) </option>
						      	<option> Fecha (desc) </option>
						    </select>
						</div>
						
						<div class="mx-1 mb-2">
						  <div class="form-check">
							  <input class="form-check-input" type="checkbox" value="videos" id="show_videos" name="show_videos">
							  <label class="form-check-label" for="defaultCheck1">
							    Videos
							  </label>
						  </div> 
						  <div class="form-check">
							  <input class="form-check-input" type="checkbox" value="playlists" id="show_playlists" name="show_playlists">
							  <label class="form-check-label" for="defaultCheck1">
							    Listas de reproduccion
							  </label>
						  </div>
						  <div class="form-check">
							  <input class="form-check-input" type="checkbox" value="channels" id="show_channels" name="show_channels">
							  <label class="form-check-label" for="defaultCheck1">
							    Canales
							  </label>
						  </div>
					    </div>
						
						
					  </div>
					  
					  <div>
					  	<input type="submit" value="Filtrar" class="btn btn-danger" ></input>
					  </div>
					</form>
            	</div>
            	
            	<% if (request.getAttribute("show_videos") != null && request.getAttribute("show_videos").equals(true)){%>
                	<div id='videos-area'>
            			<h2 class="pt-3">Videos</h2>
            			<jsp:include page="/WEB-INF/templates/videoInfo.jsp"/>
            			
            			<% 
            			List<VideoDT> videos = (List<VideoDT>) request.getAttribute("videos");
            			if (videos.size() == 0){%>
            				<p>No se han encontrado videos para tu búsqueda.</p>
            			<%}%>
            			
            		</div>
                <%}%>
            	
            	<% if (request.getAttribute("show_playlists") != null && request.getAttribute("show_playlists").equals(true)){%>
                	<div id='videos-area'>
            			<h2 class="pt-3">Playlists</h2>
            			<jsp:include page="/WEB-INF/templates/playlistInfo.jsp"/>
            			
            			<% 
            			List<PlaylistDT> playlists = (List<PlaylistDT>) request.getAttribute("playlists");
            			if (playlists.size() == 0){%>
            				<p>No se han encontrado listas para tu búsqueda.</p>
            			<%}%>
            			
            		</div>
                <%}%>
            	
            	<% if (request.getAttribute("show_channels") != null && request.getAttribute("show_channels").equals(true)){%>
                	<div id='videos-area'>
            			<h2 class="pt-3">Canales</h2>
            			<jsp:include page="/WEB-INF/templates/channelInfo.jsp"/>
            			
            			<% 
            			List<ChannelDT> channels = (List<ChannelDT>) request.getAttribute("channels");
            			if (channels.size() == 0){%>
            				<p>No se han encontrado canales para tu búsqueda.</p>
            			<%}%>
            		</div>
                <%}%>
			</div>
			      
		</div>

    <!-- jQuery CDN - Slim version (=without AJAX) -->
    <script src="/media/bootstrapResource/slim.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <!-- Popper.JS -->
    <script src="/media/bootstrapResource/popper.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    <script src="/media/bootstrapResource/bootstrap.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    
    <script type="text/javascript">
	        $(document).ready(function () {
	            $('#sidebarCollapse').on('click', function () {
	                $('#sidebar').toggleClass('active');
	            });
	        });
    </script>
</body>

</html>