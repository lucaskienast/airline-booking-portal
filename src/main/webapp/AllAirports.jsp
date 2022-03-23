<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<html>
<head>
<meta charset="UTF-8">
<title>All Airports</title>
</head>
<body>
<h3 align="center">All Airports</h3>
	<hr />
	<%
		List<String> allSources = new ArrayList<String>();
		allSources = (List<String>) session.getAttribute("allSources");
		
		List<String> allDestinations = new ArrayList<String>();
		allDestinations = (List<String>) session.getAttribute("allDestinations");
	%>
	<table border="1px" align="center">
		<tr>
			<th>Source Name</th>
		</tr>
		<%
			for (String name : allSources) {
		%>
		<tr>
			<td><%=name %></td>
		</tr>
		<%
			}
		%>
	</table>
	<br />
	<table border="1px" align="center">
		<tr>
			<th>Destination Name</th>
		</tr>
		<%
			for (String name : allDestinations) {
		%>
		<tr>
			<td><%=name %></td>
		</tr>
		<%
			}
		%>
	</table>
	<br />
	<a href="AdminDashboard.html">Admin Dashboard</a>
</body>
</html>