package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.OrderItem;

public interface OrderItemDAO {
	int insertOrderhistory(int orderId, int menuId, int quantity, int itemTotal);

    List<OrderItem> fetchOrderItems(int orderId);


	void insertOrderHistory(int userId, int orderId, int totalAmount, String status);

	int insertOrderhistory(OrderItem orderItem);

}
