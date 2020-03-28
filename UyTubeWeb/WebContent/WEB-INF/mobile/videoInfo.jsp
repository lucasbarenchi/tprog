<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="com.uytube.utils.UrlUtils, com.main.server.service.VideoDT, com.main.server.service.PlaylistDT, java.time.format.DateTimeFormatter, java.time.LocalDate, java.util.List" %>
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
        <jsp:include page="/WEB-INF/templates/sideBarMobile.jsp"/>

		<!--  Navbar  -->
        <div id="content">

            <jsp:include page="/WEB-INF/templates/navBarMobile.jsp"/>
            
			<!--  Page content  -->
            <div id='page-content'>
            	<% VideoDT videoDT = (VideoDT) request.getAttribute("video"); %>
            	<% String nickname = (String) request.getSession().getAttribute("usuario_logueado"); %>
            	<div class="col-md-9 embed-responsive embed-responsive-16by9">
				  <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/<%=UrlUtils.getVideoUrl(videoDT.getUrl())%>?rel=0" allowfullscreen></iframe>
				</div>
				<div class="card col-md-9 mt-3 py-2">
					<div class="ml-0 row align-items-center">
						<h3 class="pr-2"><%= videoDT.getTitle() %></h3>
						<% if (videoDT.isPrivate()) { %>
							<span class="badge badge-secondary px-1 py-1"><i class="fas fa-lock"></i>  PRIVADO</span>
						<% } %>
					</div>
					<div class="row ">
	      				<div class="imgContainer mt-1 mb-1 mr-0 ml-3">
	      					<a href="/users?id=<%=videoDT.getUploader()%>">
	      					<img src="/get_image?nickname=<%= videoDT.getUploader() %>" id="userImgSmall"></a>
	      				</div>
	      				<div class="col-md-7">
	        				<div class="card-block ">
	          					<h5 class="card-title mt-3 mb-0" id="videoTitle">
	          						<a href="/users?id=<%=videoDT.getUploader()%>"><%=videoDT.getUploader()%></a>
	          					</h5>
	          					<% String localDate = videoDT.getUploadDate(); %>
	          					<p>Subido el <%= localDate %></p>
	        				</div>
	      				</div>
	      				<div class="row col d-flex justify-content-end mr-4">
	      					<% boolean alreadyLiked = videoDT.getLiked().contains(nickname); %>
	      					<% boolean alreadyDisliked = videoDT.getDisliked().contains(nickname); %>
	      					<% String listOfLikes = "data-toggle=\"tooltip\" data-placement=\"bottom\" data-html=\"true\" title=\"" + String.join("<br>", videoDT.getLiked()) + "\""; %>
	      					<% String listOfDislikes = "data-toggle=\"tooltip\" data-placement=\"bottom\" data-html=\"true\" title=\"" + String.join("<br>", videoDT.getDisliked()) + "\"";  %>
	      					
							<div class="mr-1">
	      						<button type="submit" class="btn mr-0"><i class="fas fa-thumbs-up"></i> <%=videoDT.getLiked().size()%></button>
	      					</div>
	      					<div class="mr-1">
	      						<button type="submit" class="btn"><i class="fas fa-thumbs-down"></i> <%=videoDT.getDisliked().size()%></button>
	      					</div>
	      					
	      				</div>
        			</div>
       				<p class="card-text pr-4" id="videoDescription"><%= videoDT.getDescription() %></p>
       				<% if(videoDT.getCategory() != null) { %>
       					<p class="card-text pr-4" id="videoDescription">
       						<b>Categoría: </b> <%= videoDT.getCategory().getName() %>
       					</p>
       				<% } %>
       				<h5 id="videoTitle">Comentarios</h5>
       				<jsp:include page="/WEB-INF/templates/commentNode.jsp"></jsp:include>
       				<div class="my-3"></div>
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
	            
	            $('[data-toggle="tooltip"]').tooltip()
	        });
	        
	        function seeComment(elem) {
                var commentForm = $(elem).parent().parent().parent().find(".commentForm").first();
                commentForm.removeClass("invisible").addClass("visible");
                return false;
            }
	        
	        function closeComment(elem) {
                var commentForm = $(elem).parent().parent().parent();
                commentForm.removeClass("visible").addClass("invisible");
                return false;
            }
    </script>
</body>

</html>