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
	List<Access> users = (List<Access>) request.getAttribute("showAllAccess");
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
						<h1>Action</h1>
						<li><a href="/access/add">New Access</a>
					</div>
					<br>
					<div class="col-md-10">
						<table class="table">
							<tr>
								<th>ID</th>
								<th>Role</th>
								<th>Resource</th>
								<th>Created</th>
								<th>Status</th>
								<th>Actions</th>
							</tr>
							<%
								for (int idx = 0; idx < users.size(); idx++) {
							%>
							<%
								Access r = (Access) users.get(idx);
							%>
							<tr>
								<th><%=r.getId()%></th>
								<th><%=r.getName()%></th>
								<th><%=r.getNameR()%></th>
								<th><%=sdf.format(r.getDate())%></th>
								<th><%=r.isStatus()%></th>
								<th><a
									href="../access/view?action=student&ID=<%=r.getId()%>">View</a>
									<a href="../access/edit?ID=<%=r.getId()%>">Edit</a> <a
									href="/access/delete?ID=<%=r.getId()%>">Delete</a></th>
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