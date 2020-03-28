<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.main.server.service.ChannelDT"%>
<!-- channel info  -->
       <div id='page-content'>
	
        	<% List<ChannelDT> channels = (List<ChannelDT>) request.getAttribute("channels"); %>
        	<% for(ChannelDT channel : channels) { %>
        			<div class="card mt-3">
      					<div class="row ">	
      				
		      				<div class="col-md-2 imgContainer mt-1 mb-1">
		      					<a href="/users?id=<%=channel.getOwner()%>"><img src="/media/img/perfil.png" id="userImg"></a>
		      				</div>
		      				<div class="col-md-9">
		        				<div class="card-block ">
		          					<h5 class="card-title mt-3" id="videoTitle">
		          						<a href="/users?id=<%=channel.getOwner()%>"><%=channel.getName()%></a>
		          					</h5>
		          					<p class="card-text pr-4" id="channelDescription"><%= channel.getDescription()%></p>
		          					<p class="card-text pr-4 pb-2" id="channelDescription"><%= channel.getOwner()%></p>

		        				</div>
		      				</div>

        			</div>
        			</div>
        	<% } %>

</div>