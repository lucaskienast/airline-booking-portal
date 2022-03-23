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

@WebServlet("/changePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static AdminService adminService = new AdminService();
       
    public ChangePasswordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		System.out.println(password);
		AuthenticationBean beanFromUser = new AuthenticationBean();
		beanFromUser.setPassword(password);
		try {
			int rowsAffected = adminService.changePassword(beanFromUser);
			System.out.println(rowsAffected);
			if (rowsAffected == 1) {
				RequestDispatcher rd = request.getRequestDispatcher("/ChangePasswordSuccessful.html");
				rd.forward(request,  response);
			} else{ 
				RequestDispatcher rd = request.getRequestDispatcher("/ChangePasswordError.html");
				rd.forward(request,  response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
