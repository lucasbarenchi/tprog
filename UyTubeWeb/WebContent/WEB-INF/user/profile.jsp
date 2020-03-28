<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.main.server.service.UserDT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.main.server.implementation.UserWebServiceImplService, com.main.server.implementation.UserWebService"%>
    
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
	            <% UserDT user = (UserDT) request.getAttribute("user"); %>
          		<div>
          		<div class="row">
				    <div class="col-md-3">
				      <div class="col">
				        <h2 align="center"><% out.print(user.getNickname()); %></h2>
				      </div>
			          <div class="col imgContainer px-3 mt-2 mb-2">
		          		<a href="/users?id=<%=user.getNickname()%>">
		          		<img src="/get_image?nickname=<%= user.getNickname() %>" id="userImgOnProfile"></a>
		          	  </div>
				    </div>
				    <div class="col-md-5 mt-5">
				      <div class="group row">
				      	<div class="col">
					    	<h5 class="card-title mt-3" id="userName">Nombre:</h5>
					    </div>	
					    <div class="col">
					      <h5 class="card-title mt-3" id="userName2"><% out.print(user.getName()); %></h5>
					    </div>
					  </div>
					  
				      <div class="group row">
				      	<div class="col">
					    	<h5 class="card-title mt-3" id="userName">Apellido:</h5>
					    </div>	
					    <div class="col">
					      <h5 class="card-title mt-3" id="userName2"><% out.print(user.getSurname()); %></h5>
					    </div>
					  </div>
					  <div class="group row">
				      	<div class="col">
					    	<h5 class="card-title mt-3" id="userName">Fecha de nacimiento:</h5>
					    </div>	
					    <div class="col">
					      <h5 class="card-title mt-3" id="userName2"><% out.print(user.getBirthdate().toString()); %></h5>
					    </div>
					  </div>
				    </div>
				    <div class="col-md-4 mt-5">
				      <div class="group row">
				      	<div class="col">
					    	<h5 class="card-title mt-3" id="userName">Nombre del canal:</h5>
					    </div>	
					    <div class="col">
					      <h5 class="card-title mt-3" id="userName2"><% out.print(user.getChannel().getName()); %></h5>
					    </div>
					  </div>
					  
				      <div class="group row">
				      	<div class="col">
					    	<h5 class="card-title mt-3" id="userName">Privado:</h5>
					    </div>	
					    <div class="col">
					      <h5 class="card-title mt-3" id="userName2"><% if (user.getChannel().isPrivateChannel()){ out.print("Sí");} else out.print("No"); %></h5>
					    </div>
					  </div>
					  <div class="group row">
				      	<div class="col">
					    	<h5 class="card-title mt-3" id="userName">Categoría:</h5>
					    </div>	
					    <div class="col">
					      <h5 class="card-title mt-3" id="userName2"><% if (user.getChannel().getCategory() != null){
					    	  	out.print(user.getChannel().getCategory().getName());
					    	  } else {
					    	  	out.print("-");
					    	  }%></h5>
					    </div>
					  </div>
				    </div>
				</div>
				</div>
				<div>
					<ul class="nav nav-tabs">
					  <li class="nav-item ">
					    <a class="nav-link active" style="color:grey" href="#videos" data-toggle="tab">Videos</a>
					  </li>
					  <li class="nav-item">
					    <a class="nav-link" style="color:grey" href="#playlists" data-toggle="tab">Playlists</a>
					  </li>
					  <li class="nav-item">
					    <a class="nav-link" style="color:grey" href="#seguidores" data-toggle="tab">Seguidores
					    (<% out.print(((ArrayList)request.getAttribute("followers")).size()); %>)
					    </a>
					  </li>
					  <li class="nav-item">
					    <a class="nav-link" style="color:grey" href="#seguidos" data-toggle="tab">Seguidos
					     (<% out.print(((ArrayList)request.getAttribute("followeds")).size()); %>)
					    </a>
					  </li>
					  <% String userLog = (String) request.getSession().getAttribute("usuario_logueado");
							if (userLog != null && !userLog.equals(user.getNickname())){
								UserWebServiceImplService userService = new UserWebServiceImplService();
								UserWebService userWebService = userService.getUserWebServiceImplPort();
								UserDT usrLog = userWebService.getUserData(userLog);
								boolean alreadyFollow = usrLog.getFollowed().contains(user.getNickname());
								
								if (alreadyFollow){ %>
									<li class="pl-3">
									<form method="post" action="/unfollow_user?nickname=<%=user.getNickname()%>">
								  		<button class="btn btn-danger navbar-btn">Dejar de seguir</button>
								 	</form>
								 	</li> <%
								} else { %>
									<li class="pl-3">
								  	<form method="post" action="/follow_user?nickname=<%=user.getNickname()%>">
								  		<button class="btn btn-danger navbar-btn">Seguir</button>
								 	</form>
								 	</li><% 
								}
								%>
					  <%} %>
					  <% if (user.getNickname().equals(userLog)){%>
						  
						  <li class="nav-item pl-2">
						  	  <a class="btn btn-danger navbar-btn" href="/modify_user?id=<%=user.getNickname()%>">Editar perfil</a>
						  </li>
					  <% } %>
					  <% if (user.getNickname().equals(userLog)){%>
						  
						  <li class="nav-item pl-2">
						  	  <a class="btn btn-link navbar-btn" style="color:red" href="/delete_user?id=<%=user.getNickname()%>">Borrar usuario</a>
						  </li>
					  <% } %>
					  
					</ul>
					
					<div class="tab-content active">
					  <div id="videos" class="tab-pane fade in show active pt-3">
					    <h3>Videos</h3>
					    <div>
					    	<jsp:include page="/WEB-INF/templates/videoInfo.jsp"/>
					    </div>
					  </div>
					  <div id="playlists" class="tab-pane fade pt-3">
					    <h3>Playlists</h3>
					    <div>
					    	<jsp:include page="/WEB-INF/templates/playlistInfo.jsp"/>
					    </div>
					  </div>
					  <div id="seguidores" class="tab-pane fade pt-3">
					    <h3>Seguidores </h3>
					    <div>
					    	<jsp:include page="/WEB-INF/templates/followersInfo.jsp"/>
					    </div>
					  </div>
					  <div id="seguidos" class="tab-pane fade pt-3">
					    <h3>Seguidos</h3>
					    <div>
					    	<jsp:include page="/WEB-INF/templates/userInfo.jsp"/>
					    </div>
					  </div>
					</div>
				</div>
	            
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