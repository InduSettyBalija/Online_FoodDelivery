package com.foodapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodapp.model.User;
import com.foodapp.dao.UserDAO;
import com.foodapp.daoImpl.UserDAOImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("userName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		
		User u = new User(username,email,password,address);
		UserDAO udao = new UserDAOImpl();
		int x = udao.insertUser(u);
		
		if(x == 1) {
			resp.sendRedirect("home.jsp");
		}
		else {
			resp.sendRedirect("failure.html");
			
		}
		
		
		
		
	}

}
