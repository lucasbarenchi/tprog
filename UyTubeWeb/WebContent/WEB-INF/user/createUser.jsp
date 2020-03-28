<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.main.server.service.CategoryDT"%>
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
	            <h2>Registro de nuevo usuario</h2>
	            <form method="post" action="create_user" enctype="multipart/form-data">
				  <div class="form-row">
				    
				    <div class="form-group col-md-3">
				      <label for="inputNickname" style="color:white">Nickname</label>
				      <input autocomplete="off" type="text" class="form-control" id="inputNickname" name="nickname" placeholder="javiP4" required>
				      <div class="valid-feedback">
				        Usuario disponible!
				      </div>
				      <div class="invalid-feedback">
				        Usuario no disponible
				      </div>
				    </div>
				    <div class="form-group col-md-4">
				      <label for="inputMail" style="color:white">Email</label>
				      <input type="email" class="form-control" id="inputMail" name="email" placeholder="fing@eslomas.com" required>
				    </div>
				    <div class="form-group col-md-5">
				      <label for="inputImage" style="color:white">Imagen (opcional)</label>
				      <input type="file" class="form-control" name="image" id="inputMail">
				    </div>
				  </div>
				  
				  <div class="form-row">
				    <div class="form-group col-md-4">
				      <label for="inputName" style="color:white">Nombre</label>
				      <input type="text" class="form-control" id="inputName" name="nombre" placeholder="Javier" required>
				    </div>
				    <div class="form-group col-md-4">
				      <label for="inputSurname" style="color:white">Apellido</label>
				      <input type="text" class="form-control" id="inputSurname" name="apellido" placeholder="Baliosian" required>
				    </div>
				    <div class="form-group col-md-4">
				      <label for="inputBirthdate" style="color:white">Fecha de nacimiento</label>
				      <input type="date" class="form-control" id="inputSurname" name="fechaNacimiento" placeholder="aaaa" required>
				    </div>
				    	 
				  </div>
				  
				  <div class="form-row">
				    <div class="form-group col-md-6">
				      <label for="inputPassword" style="color:white">Contraseña</label>
				      <input type="password" class="form-control" id="inputPassword" name="contrasenia" placeholder="" required>
				    </div>
				    <div class="form-group col-md-6">
				      <label for="inputPassword2" style="color:white">Repetir la contraseña</label>
				      <input type="password" class="form-control" id="inputPassword2" placeholder="" oninput="check(this)" required>
				    </div>
				  </div>
				  
				  <h4 style="color:white">Información del canal</h4>
				  <div class="form-row">
				    
				    <div class="form-group col-md-4">
				      <label for="inputChannelName" style="color:white">Nombre (opcional)</label>
				      <input type="text" class="form-control" id="inputNickname" name="nombreCanal" placeholder="Javi">
				    </div>
				    
				    <div class="form-group col-md-4">
				      <label for="formCategories" style="color:white">Categoría (opcional)</label>
					    <select class="form-control" id="exampleFormControlSelect1" name="category" >
					      <option>--</option>
					      	<% List<CategoryDT> cats = (ArrayList<CategoryDT>)request.getAttribute("categories");
					      	for (CategoryDT cat : cats){%>
					      		<option> 
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
						    <input class="form-check-input" type="checkbox" name="esPrivado" id="gridCheck">
						    <label class="form-check-label" for="gridCheck" style="color:white">
						        Privado
						    </label>
					    </div>
				    </div>	
				  </div>
				  <div class="form-row">
					  <div class="form-group col-md-5">
					      <label for="inputDescripcion" style="color:white">Descripción (opcional)</label>
					      <textarea class="form-control" id="inputDescripción" name="descripcion" rows="4" placeholder="Escriba una descripción para el canal..."></textarea>
					  </div>  
				  </div>  
				  <div>
				  	<input type="submit" value="Crear" class="btn btn-danger" >Crear
				  </div>
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
    <script src="/media/bootstrapResource/jquery-3.3.1.min.js"></script>
    <!-- Popper.JS -->
    <script src="/media/bootstrapResource/popper.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    <script src="/media/bootstrapResource/bootstrap.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    
    <script type="text/javascript">
	        $(document).ready(function () {
	            $('#sidebarCollapse').on('click', function () {
	                $('#sidebar').toggleClass('active');
	            });
	            
	            $('#inputNickname').on('input', function() {
	            	if (!($('#inputNickname').val())) {
	            		$('#inputNickname').removeClass("is-valid")
	            		$('#inputNickname').removeClass("is-invalid")
	            	} else {
	            		var url = "/check_user?nickname=" + $('#inputNickname').val()
	            		$.ajax({
	            			  url: url,
	            			  success: function(data) {
	            				  	debugger;
	            				  	console.log("ORIGINAL DATA: " + data)
									if (data.includes("false")) {
										console.log("DATA: " + data)
										$('#inputNickname').removeClass("is-invalid").addClass("is-valid")
									} else if (data.includes("true")) {
										console.log("DATA: " + data)
										$('#inputNickname').removeClass("is-valid").addClass("is-invalid")
									}
								}
	            			});
	            	}
	            })
	        });
    </script>
</body>

</html>