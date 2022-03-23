<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.flyaway.flight.bean.FlightBean" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<html>
<head>
<meta charset="UTF-8">
<title>Filtered Flights</title>
</head>
<body>
<form action="bookSeatsServlet" method="POST">
	<h3 align="center">Filtered Flights</h3>
	<hr />
	<%
		List<FlightBean> filteredFlightBeans = new ArrayList<FlightBean>();
		filteredFlightBeans = (List<FlightBean>) session.getAttribute("filteredFlights");		
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
			for (FlightBean bean : filteredFlightBeans) {
		%>
		<tr>
			<td><%=bean.getFlightId() %></td>
			<td><%=bean.getDate() %></td>
			<td><%=bean.getSource() %></td>
			<td><%=bean.getDestination() %></td>
			<td><%=bean.getAirline() %></td>
			<td><%=bean.getTicketPriceUsd() %></td>
			<td><%=bean.getAvailableSeats() %></td>
		</tr>
		<%
			}
		%>
	</table>
	<p>Enter ID of the flight you want to book.</p>
	<input type="text" name="flightId" />
	<input type="submit" name="submit" value="Book" />
</form>
<br />
<a href="FlightsHome.html">Change search criteria</a>
</body>
</html>