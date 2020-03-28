<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.main.server.service.UserDT"%>

<!-- user info  -->
       <div id='page-content'>
	
        	<% List<UserDT> users;
        	if (request.getAttribute("users") != null){
        			users = (List<UserDT>) request.getAttribute("users");	
        		} else {
            		users = (List<UserDT>) request.getAttribute("followeds");	
        		}    	
    		for(UserDT user : users) { %>
        			<div class="card mt-3">
      					<div class="row ">	
      				
		      				<div class="col-md-2 imgContainer mt-1 mb-1">
		      					<a href="/users?id=<%=user.getNickname()%>">
		      					<img src="/get_image?nickname=<%= user.getNickname() %>" id="userImg"></a>
		      				</div>
		      				<div class="col-md-4">
		        				<div class="card-block">
		          					<h5 class="card-title mt-3" id="videoTitle">
		          						<a href="/users?id=<%=user.getNickname()%>"><%=user.getNickname()%></a>
		          					</h5>
		          					<p class="card-text pr-4" id="videoDescription"><%= user.getName() + " " + user.getSurname() %></p>
		          					
		        				</div>
		      				</div>

        			</div>
        			</div>
        	<% } %>

      			
	
</div>