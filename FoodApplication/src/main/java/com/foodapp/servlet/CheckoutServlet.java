package com.foodapp.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import com.foodapp.dao.OrderDAO;
import com.foodapp.daoImpl.OrderDAOImpl;
import com.foodapp.daoImpl.OrderHistoryDAOImpl;
import com.foodapp.dao.OrderHistoryDAO;
import com.foodapp.daoImpl.OrderItemDAOImpl;
import com.foodapp.dao.OrderItemDAO;
import com.foodapp.model.Cart;
import com.foodapp.model.CartItem;
import com.foodapp.model.OrderItem;
import com.foodapp.model.Order;
import com.foodapp.model.OrderHistory;
import com.foodapp.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		
		int userid=user.getUserId();
		
		if (cart != null && user != null && !cart.getItems().isEmpty()) {
		
		String paymentMethod = request.getParameter("paymentMethod");
		Integer totalAmountAttr = (Integer) session.getAttribute("totalAmount");
		Integer restaurantIdAttr = (Integer) session.getAttribute("restaurantId");

		if (totalAmountAttr == null || restaurantIdAttr == null) {
		    response.sendRedirect("cart.jsp"); // Redirect to cart page if data is missing
		    return;
		}
		
		System.out.println("Total Amount: " + totalAmountAttr);
		System.out.println("Restaurant ID: " + restaurantIdAttr);


		int orderTotalAmount = totalAmountAttr;
		Order order = new Order();
		order.setUserId(userid);
		order.setRestaurantId(restaurantIdAttr);	
		order.setPaymentOption(paymentMethod);
		order.setStatus("Pending");		
		order.setTotalAmount(orderTotalAmount);				
	    
	    OrderDAO orderDAO = new OrderDAOImpl();
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
        OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();
        
        int orderId = orderDAO.insertOrder(order);

        System.out.println("Generated Order ID: " + orderId);
        
        if (orderId > 0) {
            for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                CartItem cartItem = entry.getValue();
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(orderId);
                orderItem.setMenuId(cartItem.getItemId());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setItemTotal((int) (cartItem.getPrice() * cartItem.getQuantity()));
                orderItemDAO.insertOrderhistory(orderItem);
            }
            System.out.println("Inserted OrderItem");            
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setOrderId(orderId);
            orderHistory.setUserId(userid);
            orderHistory.setStatus("Pending");
            orderHistory.setTotalAmount(totalAmountAttr);
            orderHistory.setOrderDate(new Date(order.getDate().getTime()));
            orderHistoryDAO.insertOrderHistory(orderHistory);
            
            session.removeAttribute("cart"); // Clear the cart after order is placed
            response.sendRedirect("order_confirmation.jsp");
        } else {
            response.sendRedirect("cart.jsp");
        }
	}
	else
	{
			response.sendRedirect("cart.jsp");
	}
}


}