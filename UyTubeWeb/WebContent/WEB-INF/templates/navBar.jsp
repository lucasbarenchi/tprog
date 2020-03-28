<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Navbar  -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">

		<button type="button" id="sidebarCollapse"
			class="btn btn-danger rounded-0">
			<i class="fas fa-align-justify"></i>
		</button>
		<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
			type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<i class="fas fa-align-justify"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto ml-auto">
					<li class="nav-item">
						<div class="justify-content-md-center">
							<form class="form-inline my-2 my-lg-0 my-lg-0" action="/search"
								method="POST">
								<input class="form-control mr-sm-2 rounded-0" type="text"
									name="search_data" placeholder="Buscar..." aria-label="Search"
									style="width: 300px;">
								<button class="btn btn-danger my-2 my-sm-0 rounded-0"
									type="submit">
									<i class="fas fa-search"></i>
								</button>
							</form>
						</div>
					</li>
				</ul>
			</div>

			<ul class="nav navbar-nav ml-auto">
				<li class="nav-item active">
					<div class="dropdown">
						<a class="btn btn-danger circle-btn rounded-circle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 					
								<img class="img-btn" src="/get_image?nickname=<%= (String) request.getSession().getAttribute("usuario_logueado") %>" alt="Avatar" height="42" width="42">
						</a>
						<div
							class="dropdown-menu dropdown-menu-right rounded-0 bg-dark dropdown-user">
							<% if ((String) request.getSession().getAttribute("usuario_logueado") != null) { %>
								<div align="center" class="dropdown-item-user text-light">
									<b> 
										<%= (String) request.getSession().getAttribute("user_name") %>
									</b> <br> 
									<small> 
										<%= (String) request.getSession().getAttribute("user_email") %>
									</small>
								</div>
								<a class="dropdown-item text-light" href="/users?id=<%= (String) request.getSession().getAttribute("usuario_logueado") %>">Ver perfil</a> 
								<a class="dropdown-item text-light" href="/cerrar_sesion">Cerrar sesión</a>
							<% } else { %>
								<div align="center" class="dropdown-item-user text-light">
									<b>Invitado</b>
								</div>
								<a class="dropdown-item text-light" href="/iniciar_sesion">Iniciar sesión</a>
								<a class="dropdown-item text-light" href="/create_user">Registrarse</a>
							<% } %>
						</div>
					</div>
				</li>
			</ul>
		</div>

	</div>
</nav>
