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

import com.flyaway.service.AdminService;

@WebServlet("/airlinesServlet")
public class AirlinesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static AdminService adminService = new AdminService();
       
    public AirlinesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<String> airlinesList = new ArrayList<String>();
			airlinesList = adminService.getAllAirlines();
			if (airlinesList.size() == 0) {
				RequestDispatcher rd = request.getRequestDispatcher("/AirlinesError.html");
				rd.forward(request,  response);
			}
			if (airlinesList.size() >= 1) {
				HttpSession session = request.getSession();
				session.setAttribute("allAirlines", airlinesList);
				RequestDispatcher rd = request.getRequestDispatcher("/AllAirlines.jsp");
				rd.forward(request,  response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
