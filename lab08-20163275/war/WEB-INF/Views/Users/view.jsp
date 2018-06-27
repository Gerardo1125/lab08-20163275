<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="controller.*"%>
<%@page import="model.entity.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
	TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
	SimpleDateFormat sdf = new SimpleDateFormat();
%>
<%
	User role = (User) request.getAttribute("roles");
%>
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
<title>Insert title here</title>
</head>
<body>
	<div class="navbar-wrapper">
		<div class="container-fluid">
			<nav class="navbar navbar-fixed-top">
			<div class="container">
				<div id="navbar" class="navbar-collapse collapse" style="background-color: #e3f2fd;">
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
						<li><a href="/users">List Users</a>
					</div>
					<br>
				
					<div class="col-md-10">
						<h1>User existente</h1>
					</div>
				</div>
				<%
					} else if (role != null) {
				%>
				<div class="row">
				<br>
					<div class="col-md-2">
						<h1>Action</h1>
						<li><a href="/users">List Users</a>
					</div>
					<br>
					<div class="col-md-4">
						<table class="table">
							<tr>
								<td>ID</td>
								<td><%=role.getId() %></td>
							</tr>
							<tr>
								<td>Email</td>
								<td><%=role.getEmail() %></td>
							</tr>
							<tr>
								<td>Role</td>
								<td><%=role.getRole() %></td>
							</tr>
							<tr>
								<td>Birth</td>
								<td><%=role.getBirth() %></td>
							</tr>
							<tr>
								<td>Gender</td>
								<td><%=role.getGender()%></td>
							</tr>
							<tr>
								<td>Status</td>
								<td><%=role.isStatus() %></td>
							</tr>
							<tr>
								<td>Created</td>
								<td><%=role.getDate()%></td>
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