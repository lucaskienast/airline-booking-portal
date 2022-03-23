<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.flyaway.flight.bean.FlightBean" %>
<%@page import="com.flyaway.auth.bean.AuthenticationBean" %>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Success</title>
</head>
<body>
	<h3 align="center">Booking Successful</h3>
	<hr />
	<br/>
	<h4 align="center">Flight Details:</h4>
	<%
		FlightBean myFlightBean = new FlightBean();
		myFlightBean = (FlightBean) session.getAttribute("myFlight");	
		
		AuthenticationBean myCustomerBean = new AuthenticationBean();
		myCustomerBean = (AuthenticationBean) session.getAttribute("customerBean");	
		
		int wantedSeats = (int) session.getAttribute("wantedSeats");
		int totalPrice = wantedSeats * (int) myFlightBean.getTicketPriceUsd();
	%>
	<table border="1px" align="center">
		<tr>
			<th>Date</th>
			<th>Source</th>
			<th>Destination</th>
			<th>Airline</th>
			<th>Price per Person</th>
			<th>Wanted Seats</th>
			<th>Total Price</th>
		</tr>
		<tr>
			<td><%=myFlightBean.getDate() %></td>
			<td><%=myFlightBean.getSource() %></td>
			<td><%=myFlightBean.getDestination() %></td>
			<td><%=myFlightBean.getAirline() %></td>
			<td>$ <%=myFlightBean.getTicketPriceUsd() %></td>
			<td><%=wantedSeats %></td>
			<td>$ <%=totalPrice %></td>
		</tr>
	</table>
	<br />
	<h4 align="center">Customer Details:</h4>
	<table border="1px" align="center">
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Gender</th>
		</tr>
		<tr>
			<td><%=myCustomerBean.getName() %></td>
			<td><%=myCustomerBean.getEmail() %></td>
			<td><%=myCustomerBean.getGender() %></td>
		</tr>
	</table>
	<br />
	<a href="FlightsHome.html">Back Home</a>
</body>
</html>