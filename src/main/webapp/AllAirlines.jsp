<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<html>
<head>
<meta charset="UTF-8">
<title>All Airlines</title>
</head>
<body>
<h3 align="center">All Airlines</h3>
	<hr />
	<%
		List<String> allAirlines = new ArrayList<String>();
		allAirlines = (List<String>) session.getAttribute("allAirlines");		
	%>
	<table border="1px" align="center">
		<tr>
			<th>Airline Name</th>
		</tr>
		<%
			for (String name : allAirlines) {
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