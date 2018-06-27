
<%@ page import="controller.*"%>
<%@ page import="java.util.*"%>
<%@ page import="model.entity.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
	TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
	SimpleDateFormat sdf = new SimpleDateFormat();
%>
<%
	List<Resource> resources = (List<Resource>) request.getAttribute("viewResource");
%>
<%
	Resource resource = (Resource) request.getAttribute("resource");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UFT-8">
<title>View Resource</title>
<link rel="stylesheet" type="text/css" href="../style.css" />
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
						<li><a href="/resources">List Resources</a>
					</div>
					<br>
				
					<div class="col-md-10">
						<h1>Resource exist</h1>
					</div>
				</div>
				<%
					} else if (resource != null) {
				%>
				<div class="row">
				<br>
					<div class="col-md-2">
						<h1>Action</h1>
						<li><a href="/resources">List Resources</a>
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
								<td>Status</td>
								<td><%=resource.isStatus() %></td>
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
