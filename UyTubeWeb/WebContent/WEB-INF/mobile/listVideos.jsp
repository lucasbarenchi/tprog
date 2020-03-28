<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List, com.main.server.service.VideoDT" %>
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
        <jsp:include page="/WEB-INF/templates/sideBarMobile.jsp"/>

<!--         Navbar  -->
        <div id="content">

            <jsp:include page="/WEB-INF/templates/navBarMobile.jsp"/>
            
<!--             Page content  -->
            <div id='page-content'>
            	<div class="form-row">
            		<form class="form-inline my-2 my-lg-0 my-lg-0" action="/search" method="POST">
						<div>
							<input class="form-control mr-sm-2 rounded-0" type="text"
								name="search_data" placeholder="Buscar..." aria-label="Search">
								
							<button class="btn btn-danger my-2 my-sm-0 rounded-0" type="submit">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</form>
            	</div>
            	
				<jsp:include page="/WEB-INF/templates/videoInfo.jsp"/>
					
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