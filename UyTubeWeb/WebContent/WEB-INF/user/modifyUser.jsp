<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.main.server.service.CategoryDT,com.main.server.service.VideoDT,com.main.server.service.PlaylistDT"%>
<%@page import="com.main.server.service.UserDT,java.time.LocalDate,java.time.format.DateTimeFormatter"%>
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
    
<!--         Sidebar  -->
        <jsp:include page="/WEB-INF/templates/sideBar.jsp"/>

<!--         Navbar  -->
        <div id="content">

            <jsp:include page="/WEB-INF/templates/navBar.jsp"/>
            
<!--             Page content  -->
            <div id='page-content'>
            	<% UserDT userDT = (UserDT) request.getAttribute("user"); %>
            
	            <h2>Modificación de usuario</h2>
	            <div class="row mt-3 mb-0">
	            	<div class="form-group col-md-3">
					    <h5 for="inputNickname" style="color:white">Nickname</h5>
					    <label for="inputNickname" style="color:white"><%= userDT.getNickname() %></label>
					</div>
					<div class="form-group col-md-4">
						<h5 for="inputMail" style="color:white">Email</h5>
					    <label for="inputMail" style="color:white"><%= userDT.getMail() %></label>
					</div>
	            </div>
				
	            <form method="post" action="modify_user?id=<%= userDT.getNickname() %>" enctype="multipart/form-data">
				  <div class="my-3 form-row">
				  	<div class="mt-1 mb-1 mr-4 ml-3">
	      				<img src="/get_image?nickname=<%= userDT.getNickname() %>" id="userImg">
	      			</div>
				    <div class="form-group col-md-5">
				      <label for="inputImage" style="color:white">Imagen</label>
				      <input type="file" class="form-control" name="image" id="inputMail">
				    </div>
				  </div>
				  
				  <div class="form-row">
				    <div class="form-group col-md-4">
				      <label for="inputName" style="color:white">Nombre</label>
				      <input type="text" class="form-control" id="inputName" name="nombre" placeholder="Javier" value="<%= userDT.getName() %>" required>
				    </div>
				    <div class="form-group col-md-4">
				      <label for="inputSurname" style="color:white">Apellido</label>
				      <input type="text" class="form-control" id="inputSurname" name="apellido" placeholder="Baliosian" value="<%= userDT.getSurname() %>" required>
				    </div>
				    <div class="form-group col-md-4">
				      <label for="inputBirthdate" style="color:white">Fecha de nacimiento</label>
				      <% LocalDate date = LocalDate.parse(userDT.getBirthdate()); %>
				      <% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); %>
				      <% String dateString = date.format(formatter); %>
				      <input type="date" class="form-control" id="inputSurname" name="fechaNacimiento" placeholder="aaaa" value="<%= dateString %>" required>
				    </div>
				    	 
				  </div>
				  
				  <div class="form-row">
				    <div class="form-group col-md-6">
				      <label for="inputPassword" style="color:white">Nueva contraseña</label>
				      <input type="password" class="form-control" id="inputPassword" name="contrasenia" placeholder="">
				    </div>
				    <div class="form-group col-md-6">
				      <label for="inputPassword2" style="color:white">Repetir la nueva contraseña</label>
				      <input type="password" class="form-control" id="inputPassword2" placeholder="" oninput="check(this)">
				    </div>
				  </div>
				  
				  <h4 style="color:white">Información del canal</h4>
				  <div class="form-row">
				    
				    <div class="form-group col-md-4">
				      <label for="inputChannelName" style="color:white">Nombre (opcional)</label>
				      <input type="text" class="form-control" id="inputNickname" name="nombreCanal" placeholder="Javi" value="<%= userDT.getChannel().getName() %>">
				    </div>
				    
				    <div class="form-group col-md-4">
				      <label for="formCategories" style="color:white">Categoría (opcional)</label>
					    <select class="form-control" id="exampleFormControlSelect1" name="category" >
					      <option>--</option>
					      	<% List<CategoryDT> cats = (ArrayList<CategoryDT>)request.getAttribute("categories");
					      	for (CategoryDT cat : cats){%>
					      		<option <%= userDT.getChannel().getCategory() != null && userDT.getChannel().getCategory().getName() == cat.getName() ? "selected" : "" %> > 
					      			<% out.print(cat.getName()); %>
					      		</option>
					      	<%}%>
					    </select>
				    </div>
				    
				    <div class="form-check">
					    <div>
					    	<label style="color:white">Privacidad del canal</label>
					    </div>
					    <div class="form-group col-md-5">
						    <input class="form-check-input" type="checkbox" name="esPrivado" id="gridCheck" <%= userDT.getChannel().isPrivateChannel() ? "checked" : "" %>>
						    <label class="form-check-label" for="gridCheck" style="color:white">
						        Privado
						    </label>
					    </div>
				    </div>	
				  </div>
				  <div class="form-row">
					  <div class="form-group col-md-5">
					      <label for="inputDescripcion" style="color:white">Descripción (opcional)</label>
					      <textarea class="form-control" id="inputDescripción" name="descripcion" rows="4" placeholder="Escriba una descripción para el canal..."><%= userDT.getChannel().getDescription() %></textarea>
					  </div>  
				  </div>  
				  <div>
				  	<input type="submit" value="Modificar" class="btn btn-danger" >
				  </div>
				</form>
				<form class="form-inline mt-4" action="modify_video">
					<div class="form-group col-md-9">
				      	<label class="mr-4" for="formCategories" style="color:white">Videos de <%= userDT.getNickname() %></label>
					    <select class="form-control" id="exampleFormControlSelect1" name="id" >
					      	<% List<VideoDT> videos = userDT.getChannel().getVideos();
					      	for (VideoDT video : videos){%>
					      		<option value="<%= video.getVideoId() %>"> 
					      			<% out.print(video.getTitle()); %>
					      		</option>
					      	<%}%>
					    </select>
				    </div>
				    <input type="submit" class="btn btn-danger" value="Modificar video" style="color:white" />
				</form>
				<form class="form-inline mt-4" action="playlist">
					<div class="form-group col-md-9">
				      	<label class="mr-4" for="formCategories" style="color:white">Listas de reproducción de <%= userDT.getNickname() %></label>
				      	<input type="hidden" name="user" value="<%= userDT.getNickname() %>" />
					    <select class="form-control" id="exampleFormControlSelect1" name="id" >
					      	<% List<PlaylistDT> playlists = (List<PlaylistDT>) request.getAttribute("playlists");
					      	for (PlaylistDT playlist : playlists){%>
					      		<option value="<%= playlist.getPlaylistId() %>"> 
					      			<% out.print(playlist.getName()); %>
					      		</option>
					      	<%}%>
					    </select>
				    </div>
				    <input type="submit" class="btn btn-danger" value="Modificar lista" style="color:white" />
				</form>
	        </div>
        </div>
    </div>
    
    <script type='text/javascript'>
    function check(input) {
        if (input.value != document.getElementById('inputPassword').value) {
            input.setCustomValidity('Las contraseñas no coinciden');
        } else {
            // input is valid -- reset the error message
            input.setCustomValidity('');
        }
    }
	</script>

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