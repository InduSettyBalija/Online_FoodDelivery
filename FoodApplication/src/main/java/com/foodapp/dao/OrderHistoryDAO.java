package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.OrderHistory;

public interface OrderHistoryDAO {
	OrderHistory fetchOrderHistoryById(int orderHistoryId);  // Fetch order history by orderhistory_id

    int insertOrderHistory(OrderHistoryDAO orderHistory);  // Insert order history details

    int updateOrderHistoryStatusById(int orderHistoryId, String status);  // Update status by orderhistory_id

    List<OrderHistory> fetchOrderHistoryByUserId(int userId);  // Fetch order history by user_id

	int insertOrderHistory(OrderHistory orderHistory);

}
