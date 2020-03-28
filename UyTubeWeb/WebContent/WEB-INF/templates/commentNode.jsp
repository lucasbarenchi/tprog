<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.main.server.service.CommentDT, java.util.List, java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>

<% CommentDT comment = (CommentDT) request.getAttribute("comments"); %>
<% List<CommentDT> children = comment.getResponses(); %>
<% for(CommentDT c : children) { %>
	<div>
		<div class="row">
			<div class="imgContainer mt-1 mb-1 mr-0 ml-3">
				<a href="/users?id=<%=c.getCommenter()%>"><img src="/get_image?nickname=<%= c.getCommenter() %>" id="userImgSmall"></a>
			</div>
			<div class="card-block">
				<div class="row ml-2 mt-3 mb-0 align-text-top">
					<p class="card-title mr-2 mb-0" id="videoTitle" style="font-size:0.9em">
						<a href="/users?id=<%=c.getCommenter()%>"><b><%=c.getCommenter()%></b></a>
					</p>
					<% String formattedString = c.getCommentDate(); %>
					<p class="mb-0"><%= formattedString %></p>
				</div>
				<p class="ml-2 mb-0"><%= c.getText() %></p>
				<% if (request.getSession().getAttribute("usuario_logueado") != null) { %>
				<a class="ml-2 white" id="toggleAnswer" href="javascript:void(0)" onclick="seeComment(this)">Responder</a>
				<% } %>
			</div>
		</div>
		<div class="ml-2 commentForm invisible">
			<form class="mt-2" method="post" action="/comment_comment?comment_id=<%=c.getCommentId()%>&id=<%= request.getParameter("id") %>">
   				<div class="mr-4">
			    	<textarea class="form-control" rows="2" name="text"></textarea>
			    	<button class="btn btn-danger my-2 mr-2" type="button" onclick="closeComment(this)">Cancelar</button>
   					<button type="submit" class="btn btn-danger my-2">Enviar</button>
    			</div>
   			</form>
		</div>
		
		<% request.setAttribute("comments", c); %>
		<div class="pl-3 ml-5 mb-0 pb-0">
			<jsp:include page="/WEB-INF/templates/commentNode.jsp"></jsp:include>
		</div>
	</div>
<% } %>