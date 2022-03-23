package com.flyaway.flight.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.service.FlightService;
import com.flyaway.flight.bean.FlightBean;

@WebServlet("/bookSeatsServlet")
public class BookSeatsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static FlightService flightService = new FlightService();
       
    public BookSeatsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("flightId"));
		HttpSession session = request.getSession();
		int wantedSeats = (int) session.getAttribute("wantedSeats");
		System.out.println(wantedSeats);
		List<FlightBean> filteredFlights = (List<FlightBean>) session.getAttribute("filteredFlights");
		FlightBean targetFlight = new FlightBean();
		for (FlightBean bean : filteredFlights) {
			if (bean.getFlightId() == (Integer.parseInt(request.getParameter("flightId")))) {
				targetFlight = bean;
			}
		}
		try {
			int rowsAffected = flightService.bookSeatsOnFlight(targetFlight, wantedSeats);
			if (rowsAffected >= 1) {
				session.setAttribute("myFlight", targetFlight);
				RequestDispatcher rd = request.getRequestDispatcher("/Register.html");
				rd.forward(request,  response);
			} else{ 
				RequestDispatcher rd = request.getRequestDispatcher("/LoginError.html");
				rd.forward(request,  response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
