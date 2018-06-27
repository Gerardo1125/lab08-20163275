<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="controller.*"%>
<%@page import="model.entity.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	List<Role> roles = (List<Role>) request.getAttribute("showAllRoles");
%>
<%
	Role r = (Role) request.getAttribute("role");
%>
<%
	User role = (User) request.getAttribute("student");
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
				<div id="navbar" class="navbar-collapse collapse"
					style="background-color: #e3f2fd;">
					<ul class="nav navbar-nav pull-right">
						<li><a href="../roles">Roles</a></li>
						<li><a href="../users">Users</a></li>
						<li><a href="../reources">Resources</a></li>
						<li><a href="/access">Access</a></li>
						<li><a href="/students">Students</a></li>
						<li ><a href="/users/login">Login</a></li>
						<li ><a href="/users/logout">Logout</a></li>
					</ul>
				</div>

				<div class="row">
					<br>
					<div class="col-md-2">
						<h1>Action</h1>
						<li><a href="/users">List Users</a>
					</div>
					<br>
					<div class="col-md-3">
						<form action="../users/edit" method="post">

							<div class="form-check">
								<label class="form-check-label" for="exampleCheck1">ID</label> <input
									type="text" class="form-control" id="name"
									aria-describedby="emailHelp" name="ID"
									value="<%=role.getId()%>" readonly>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Role</label> <select
									class="form-control" id="sel1" name="role">
									<%
										for (int i = 0; i < roles.size(); i++) {
											Role ro = (Role) roles.get(i);
											if (ro.getId().equals(r.getId())) {
									%>
									<option selected="selected"><%=ro.getName()%></option>
									<%
										} else {
									%>
									<option><%=ro.getName()%></option>
									<%
										}
										}
									%>
								</select>

							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Name</label> <input type="text"
									class="form-control" id="name" aria-describedby="emailHelp"
									name="email" placeholder="Enter email" requerid="requerid"
									value="<%=role.getEmail()%>">
							</div>
							<div class="form-check">
								<label class="form-check-label" for="exampleCheck1">Birth</label>
								<input type="date" class="form-control" id="name"
									aria-describedby="emailHelp" name="date"
									value="<%=role.getBirth()%>">
							</div>
							<% 
								if(role.getGender().equalsIgnoreCase("hombre")){
							%>
								<div class="form-group">
								<label for="exampleInputEmail1">Gender</label>
								<div class="radio">
									<label><input type="radio" value="hombre" name="gender" checked>Hombre</label>
								</div>
								<div class="radio">
									<label><input type="radio"value="mujer" name="gender">Mujer</label>
								</div>
							</div>
							<%}else{ %>
								<div class="form-group">
								<label for="exampleInputEmail1">Gender</label>
								<div class="radio">
									<label><input type="radio" value="hombre" name="gender">Hombre</label>
								</div>
								<div class="radio">
									<label><input type="radio"value="mujer" name="gender" checked>Mujer</label>
								</div>
							</div>
							<%} %>
							<%
								if (role.isStatus()) {
							%>
							<div class="form-check">
								<input type="checkbox" class="form-check-input"
									id="exampleCheck1" name="status" checked> <label
									class="form-check-label" for="exampleCheck1">Status</label>
							</div>

							<%
								} else {
							%>
							<div class="form-check">
								<input type="checkbox" class="form-check-input"
									id="exampleCheck1" name="status"> <label
									class="form-check-label" for="exampleCheck1">Status</label>
							</div>
							<%
								}
							%>
							<input type="hidden" name="action" value="edit" />
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
					</div>
				</div>

			</div>
			</nav>
		</div>
	</div>
</body>
</html>