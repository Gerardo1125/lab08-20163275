<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Hello App Engine</title>
</head>

<body>
	<div class="navbar-wrapper">
		<div class="container-fluid">
			<nav class="navbar navbar-fixed-top">
			<div class="container">
				<div id="navbar" class="navbar-collapse collapse"
					style="background-color: #e3f2fd;">
					<ul class="nav navbar-nav pull-right">
						<li><a href="/roles" active>Roles</a></li>
						<li><a href="/users">Users</a></li>
						<li><a href="/resources">Resources</a></li>
						<li><a href="/access">Access</a></li>
						<li><a href="/students">Students</a></li>
						<li><a href="/users/login">Login</a></li>
						<li><a href="/users/logout">Logout</a></li>

					</ul>
				</div>
				<div class="row">
					<br>
					<div class="col-md-2"></div>
					<br>
					<div class="col-md-10">
						<%if(request.getAttribute("error").equals("login")){ %>
							<h2>Tiene que ingresar con un correo</h2>
						<%}else if(request.getAttribute("error").equals("noExiste")){ %>
							<hi>No esta registrado en Users</hi>
							<li>El administrador solo puede crear Users</li>
						<%}else if(request.getAttribute("error").equals("sinPermiso")){ %>
							<li>No existe el resource</li>
							<li>El administrador solo puede crear Resource</li>
						<%}else if(request.getAttribute("error").equals("sinAcceso")){ %>
							<li>No tiene un access</li>
							<li>El administrador solo puede crear Access</li>
						<%}else if(request.getAttribute("error").equals("sinPermisoEdit")){%>
							<li>No existe el resource para editar</li>
						<%}else if(request.getAttribute("error").equals("sinAccesoEdit")){%>
							<li>No tiene acceso para editar</li>
						<%}else if(request.getAttribute("error").equals("sinPermisoDelete")){%>
							<li>No existe el resource eliminar</li>
						<%}else if(request.getAttribute("error").equals("sinAccesoDelete")){%>
							<li>No tiene acceso para eliminar</li>
						<%}else if(request.getAttribute("error").equals("deleteA")){%>
							<li>No puede eliminar el administrador</li>
						<%}else if(request.getAttribute("error").equals("errorA")){%>
							<li>No es administrador</li>
						<%}else if(request.getAttribute("error").equals("adminP")){%>
							<li>No es administrador....</li>
						<%}else if(request.getAttribute("error").equals("sinAccesoAdd")){%>
							<li>No tiene acceso para añadir</li>
						<%} %>
						
					</div>
				</div>
			</div>
			</nav>
		</div>
	</div>
</body>
</html>
</html>