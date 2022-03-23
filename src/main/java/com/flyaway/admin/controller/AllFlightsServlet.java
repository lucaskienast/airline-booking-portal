package com.flyaway.admin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.flight.bean.FlightBean;
import com.flyaway.service.AdminService;

@WebServlet("/allFlightsServlet")
public class AllFlightsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static AdminService adminService = new AdminService();
       
    public AllFlightsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<FlightBean> allFlights = new ArrayList<FlightBean>();
			allFlights = adminService.getAllFlights();
			if (allFlights.size() == 0) {
				RequestDispatcher rd = request.getRequestDispatcher("/AdminFlightsError.html");
				rd.forward(request,  response);
			}
			if (allFlights.size() >= 1) {
				HttpSession session = request.getSession();
				session.setAttribute("allFlights", allFlights);
				RequestDispatcher rd = request.getRequestDispatcher("/AllFlights.jsp");
				rd.forward(request,  response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
