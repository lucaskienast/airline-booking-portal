<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.flyaway.flight.bean.FlightBean" %>
<%@page import="com.flyaway.auth.bean.AuthenticationBean" %>
<html>
<head>
<meta charset="UTF-8">
<title>Final Overview</title>
</head>
<body>
	<h3 align="center">Final Overview</h3>
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
		</tr>
		<tr>
			<td><%=myFlightBean.getDate() %></td>
			<td><%=myFlightBean.getSource() %></td>
			<td><%=myFlightBean.getDestination() %></td>
			<td><%=myFlightBean.getAirline() %></td>
			<td>$ <%=myFlightBean.getTicketPriceUsd() %></td>
			<td><%=wantedSeats %></td>
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
	<h4 align="center">Payment:</h4>
	<form action="paymentServlet" method="post">
		<table align="center">
			<tr>
				<td>Total Price<td>
				<td><p>$ <%=totalPrice %></p><td>
			</tr>
			<tr>
				<td>Card Number<td>
				<td><input type="number" name="cardNumber"/><td>
			</tr>
			<tr>
				<td>Name on Card<td>
				<td><input type="text" name="nameOnCard"/><td>
			</tr>
			<tr>
				<td>Expiry Date<td>
				<td><input type="text" name="cardExpiryDate"/><td>
			</tr>
			<tr>
				<td>Security Code<td>
				<td><input type="password" name="cardCode"/><td>
			</tr>
			<tr>
				<td align="center"><input type="submit" name="Pay"/><td>
			</tr>
		</table>
	</form>
</body>
</html>