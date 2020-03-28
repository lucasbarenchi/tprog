<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

			<!--  Navbar  -->
	        <div id="content">
	
	            <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="text-align:center">
					<h3 style="text-align:center">Iniciar sesión</h3>
				</nav>
				
	            
				<!--  Page content  -->
	            <div id='page-content'>
	            	<div class="navbar navbar-expand-lg navbar-dark bg-dark">
	            		<form action="/iniciar_sesion" method="POST">
							<div class="form-group">
							<%
				            String toShow = null;
				            if (request.getParameter("nickname") == null || request.getParameter("nickname").trim() == ""){
				            	if (request.getAttribute("userRemembered") != ""){
				            		toShow = (String)request.getAttribute("userRemembered");
				            	}	            			
				            } else {
				            	toShow = request.getParameter("nickname");
				            }%>
								<label for="exampleInputEmail1">Nickname</label> <input
									name="nickname"
									value=<%=toShow == null ? "\"\"" : toShow %>
									class="form-control ${userValid}"
									placeholder="Ingresar nickname o email">
								<div class="invalid-feedback">Usuario inválido</div>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Contraseña</label> <input
									name="password"
									value=<%= request.getParameter("password") == null || 
										request.getParameter("password").trim() == "" ? "\"\"" : request.getParameter("password") %>
									type="password" class="form-control ${passValid}"
									placeholder="Ingresar contraseña">
								<div class="invalid-feedback">Contraseña invalida</div>
		
							</div>
							<div class="form-check">
							    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="rememberMe">
							    <label class="form-check-label" for="exampleCheck1">Recordarme</label>
							</div>
							<br>
							<button type="submit" class="btn btn-danger">Ingresar</button>
						</form>
	            	</div>
				</div>
				      
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