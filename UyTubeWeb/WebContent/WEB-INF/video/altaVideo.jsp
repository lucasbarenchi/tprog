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
	            <h2>Subir video</h2>
				<form method="post" action="/create_video">
				  <div class="form-row">
				    <div class="form-group col-md-4">
				      <label for="videoName" style="color:white">Nombre del video</label>
				      <input type="text" name="video_name" class="form-control" id="inputName" placeholder="Top 10 videos más graciosos" required>
				    </div>
				    <div class="form-group col-md-4">
				      <label for="duration" style="color:white">Duración (en segundos)</label>
				      <input type="number" name="length" class="form-control" id="inputLength" placeholder="45" required>
				    </div>
				    <div class="form-group col-md-4">
				      <label for="publicDate" style="color:white">Fecha de publicación</label>
				      <input type="date" name="upload_date" class="form-control" id="inputPublicDate" placeholder="aaaa" required>
				    </div>
				  </div>
				  
				  <div class="form-row">
				    <div class="form-group col">
				      <label for="url" style="color:white">URL del video</label>
				      <input type="text" name="url" class="form-control" id="inputURL" placeholder="https://www.youtube.com/watch?v=vu_mfmNHtYM" required>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="description">Descripción</label>
				    <textarea name="description" class="form-control" id="inputDesc" rows="3" required></textarea>
				  </div>
				  
				  <div class="form-group">
				    <label for="category">Categoría (opcional)</label>
				    <select name="category" class="form-control" id="inputCategory">
				      	<option> - </option>
				      	<% List<CategoryDT> cats = (ArrayList<CategoryDT>)request.getAttribute("categories");
				      	for (CategoryDT cat : cats){%>
				      		<option> 
				      			<% out.print(cat.getName()); %>
				      		</option>
				      	<%}%>
				    </select>
				  </div>
				  
				  <div>
				  	<input type="submit" value="Publicar" class="btn btn-danger" >Publicar</input>
				  </div>
				</form>
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