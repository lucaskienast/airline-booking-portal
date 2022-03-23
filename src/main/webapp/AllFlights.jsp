<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.flyaway.flight.bean.FlightBean" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<html>
<head>
<meta charset="UTF-8">
<title>All Flights</title>
</head>
<body>
	<h3 align="center">All Flights</h3>
	<hr />
	<%
		List<FlightBean> allFlightBeans = new ArrayList<FlightBean>();
		allFlightBeans = (List<FlightBean>) session.getAttribute("allFlights");		
	%>
	<table border="1px" align="center">
		<tr>
			<th>Flight Id</th>
			<th>Date</th>
			<th>Source</th>
			<th>Destination</th>
			<th>Airline</th>
			<th>Price per Person</th>
			<th>Available Seats</th>
		</tr>
		<%
			for (FlightBean bean : allFlightBeans) {
		%>
		<tr>
			<td><%=bean.getFlightId() %></td>
			<td><%=bean.getDate() %></td>
			<td><%=bean.getSource() %></td>
			<td><%=bean.getDestination() %></td>
			<td><%=bean.getAirline() %></td>
			<td>$ <%=bean.getTicketPriceUsd() %></td>
			<td><%=bean.getAvailableSeats() %></td>
		</tr>
		<%
			}
		%>
	</table>
	<br />
	<a href="AdminDashboard.html">Admin Dashboard</a>
</body>
</html>