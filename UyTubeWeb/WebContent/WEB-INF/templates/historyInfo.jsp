<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.main.server.service.HistoryDT, com.uytube.utils.UrlUtils" %>
<% String usuarioLogueado = (String) request.getSession().getAttribute("usuario_logueado"); %>
<% List<HistoryDT> historial = (List<HistoryDT>) request.getAttribute("history_data"); %>

<% for(HistoryDT historyDt : historial) { %>
	<div class="card mt-3">
      <div class="row">
          <div class="col-md-3 imgContainer px-2 mt-2 mb-2">
         		<a href="/video?id=<%=historyDt.getVideoDt().getVideoId().toString()%>">
         			<img src="https://img.youtube.com/vi/<%=UrlUtils.getVideoUrl(historyDt.getVideoDt().getUrl())%>/hqdefault.jpg" id="videoImg" class="w-100">
         		</a>
         </div>
          <div class="col-md-9 px-3">
            <div class="card-block py-2 d-flex flex-column">
            <div> 
              <a class="card-title pr-2 align-middle" id="videoTitle" href="/video?id=<%=historyDt.getVideoDt().getVideoId().toString()%>"><%=historyDt.getVideoDt().getTitle()%></a>
				<%
					if (historyDt.getVideoDt().isPrivate()) {
				%>
					<span class="badge badge-secondary px-1 py-1"><i class="fas fa-lock"></i>  PRIVADO</span>
				<%
					}
				%>	 
				
	          	</div>
              	<p class="card-text pr-4" id="videoDescription"><%= historyDt.getVideoDt().getDescription() %></p>
              	<p class="card-text pr-4 mt-auto"><b>Subido por:</b> <%= historyDt.getVideoDt().getUploader() %></p>
              	<p class="card-text pr-4 mt-auto"><b>Cantidad de mis visitas:</b> <%= historyDt.getVisitCount() %></p>
              	<p class="card-text pr-4 mt-auto"><b>Última visita:</b> <%= historyDt.getLastVisit() %></p>
            </div>
          </div>

        </div>
      </div>
 <% } %>