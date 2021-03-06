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
	List<Resource> resources = (List<Resource>) request.getAttribute("showAllResource");
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
						<li><a href="/roles">Roles</a></li>
						<li><a href="/users">Users</a></li>
						<li><a href="/resources">Resources</a></li>
						<li><a href="/access">Access</a></li>
						<li><a href="/students">Students</a></li>
						<li ><a href="/users/login">Login</a></li>
						<li ><a href="/users/logout">Logout</a></li>
					</ul>
				</div>

				<div class="row">
					<br>
					<div class="col-md-2">
						<h1>Actions</h1>
						<li><a href="/access">List Access</a>
					</div>
					<br>
					<div class="col-md-3">
						<form action="../access/add" method="post">
							<div class="form-group">
								<label for="exampleInputEmail1">Role</label> <select
									class="form-control" id="sel1" name="role">
									<%
										for (int i = 0; i < roles.size(); i++) {
											Role r = (Role) roles.get(i);
									%>
									<option><%=r.getName()%></option>
									<%
										}
									%>
								</select>

							</div>

							<div class="form-group">
								<label for="exampleInputEmail1">Resource</label> <select
									class="form-control" id="sel1" name="resource">
									<%
										for (int i = 0; i < resources.size(); i++) {
											Resource r = (Resource) resources.get(i);
									%>
									<option><%=r.getName()%></option>
									<%
										}
									%>
								</select>

							</div>
							
							<div class="form-check">
								<input type="checkbox" class="form-check-input"
									id="exampleCheck1" name="status" checked> <label
									class="form-check-label" for="exampleCheck1">Status</label>
							</div>

							<input type="hidden" name="action" value="create" />
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