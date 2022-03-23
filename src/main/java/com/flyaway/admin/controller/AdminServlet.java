package com.flyaway.admin.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.auth.bean.AuthenticationBean;
import com.flyaway.service.AdminService;

@WebServlet("/adminServlet")
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static AdminService adminService = new AdminService();
       
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		AuthenticationBean beanFromUser = new AuthenticationBean();
		beanFromUser.setEmail(email);
		beanFromUser.setPassword(password);
		try {
			String userType = adminService.login(beanFromUser);
			if ("admin".equals(userType)) {
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				RequestDispatcher rd = request.getRequestDispatcher("/AdminDashboard.html");
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
