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
	List<Role> roles = (List<Role>) request.getAttribute("showAllRoles");
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
			<nav class="navbar navbar-fixed-top" >
			<div class="container">
				<div id="navbar" class="navbar-collapse collapse" style="background-color: #e3f2fd;">
					<ul class="nav navbar-nav pull-right">
						<li><a href="/roles" active>Roles</a></li>
						<li><a href="/users">Users</a></li>
						<li><a href="/resources">Resources</a></li>
						<li><a href="/access">Access</a></li>
						<li><a href="/students">Students</a></li>
						<li ><a href="/users/login">Login</a></li>
						<li ><a href="/users/logout">Logout</a></li>

					</ul>
				</div>
				<div class="row" >
					<br>
					<div class="col-md-2" >
						<h1>Action</h1>
						<li><a href="/roles/add">New Role</a>
					</div>
					<br>
					<div class="col-md-10">
						<table class="table">
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Status</th>
								<th>Created</th>
								<th>Actions</th>
							</tr>
							<%
								for (int idx = 0; idx < roles.size(); idx++) {
							%>
							<%
								Role r = (Role) roles.get(idx);
							%>
							<tr>
								<th><%=r.getId()%></th>
								<th><%=r.getName()%></th>
								<th><%=r.isStatus()%></th>
								<th><%=sdf.format(r.getDate())%></th>
								<th><a
									href="../roles/view?action=student&ID=<%=r.getId()%>">View</a>
									<a href="/roles/edit?ID=<%=r.getId()%>">Edit</a> <a
									href="/roles/delete?ID=<%=r.getId()%>">Delete</a></th>
							</tr>
							<%
								}
							%>
						</table>
					</div>
				</div>
			</div>
			</nav>
		</div>
	</div>
</body>
</html>
</html>