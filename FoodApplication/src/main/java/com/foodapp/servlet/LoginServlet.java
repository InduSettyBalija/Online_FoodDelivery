package com.foodapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodapp.model.User;
import com.foodapp.dao.UserDAO;
import com.foodapp.daoImpl.UserDAOImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// Call UserDAOImpl to validate user
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUserById(email);

		if (user != null && user.getPassword().equals(password)) {
			// Set session for the logged-in user
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			// Redirect to the HomeServlet to load restaurant list
			RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeServlet");
			dispatcher.forward(request, response);
		} else {
			// Invalid login, redirect to failure page
			response.sendRedirect("failure.html");
		}
	}

}
