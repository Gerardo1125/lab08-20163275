
<%@ page import="controller.*"%>
<%@ page import="java.util.*"%>
<%@ page import="model.entity.Student"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
	TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
	SimpleDateFormat sdf = new SimpleDateFormat();
%>
<%
	List<Student> resources = (List<Student>) request.getAttribute("showAllStudents");
%>
<%
	Student resource = (Student) request.getAttribute("student");
%>
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
<link rel="stylesheet" type="text/css" href="../style.css" />
</head>
<body>
	<div class="navbar-wrapper">
		<div class="container-fluid">
			<nav class="navbar navbar-fixed-top">
			<div class="container">
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav pull-right" >
						<li><a href="../roles">Roles</a></li>
						<li><a href="../users">Users</a></li>
						<li><a href="../resources">Resources</a></li>
						<li><a href="../access">Access</a></li>
						<li><a href="/students">Students</a></li>
						<li ><a href="/users/login">Login</a></li>
						<li ><a href="/users/logout">Logout</a></li>
					</ul>
				</div>
		
				<%
					if (request.getAttribute("condicion") != null) {
				%>
				<div class="row">
				<br>
				<br>
					<div class="col-md-2">
						<h1>Action</h1>
						<li><a href="/resources">List Resources</a>
					</div>
					<br>
					<%if(request.getAttribute("condicion").equals("dni")){ %>
					<div class="col-md-10">
						<h1>Usuario ya existente con el mismo dni</h1>
					</div>
					<%}else if(request.getAttribute("condicion").equals("email")){ %>
						<div class="col-md-10">
						<h1>Usuario ya existente con el mismo email</h1>
					</div>
					<%}else if(request.getAttribute("condicion").equals("phone")){ %>
						<div class="col-md-10">
						<h1>Usuario ya existente con el mismo telefono</h1>
					</div>
					<%} %>
				</div>
				<%
					} else if (resource != null) {
				%>
				<div class="row">
				<br>
					<div class="col-md-2">
						<h1>Action</h1>
						<li><a href="/students">List Students</a>
					</div>
					<br>
					<div class="col-md-4">
						<table class="table">
						
							<tr>
								<td>ID</td>
								<td><%=resource.getId() %></td>
							</tr>
							<tr>
								<td>Name</td>
								<td><%=resource.getName() %></td>
							</tr>
							<tr>
								<td>Phone</td>
								<td><%=resource.getPhone() %></td>
							</tr>
							<tr>
								<td>DNI</td>
								<td><%=resource.getDNI() %></td>
							</tr>
							<tr>
								<td>Email</td>
								<td><%=resource.getEmail() %></td>
							</tr>
							<tr>
								<td>Birth</td>
								<td><%=resource.getBirth() %></td>
							</tr>
							<tr>
								<td>Gender</td>
								<td><%=resource.isGender() %></td>
							</tr>
							<tr>
								<td>Created</td>
								<td><%=resource.getDate() %></td>
							</tr>
						</table>
					</div>
				</div>
				<%
					}
				%>
			</div>
			</nav>
		</div>
	</div>
	
</body>
</html>
