package com.foodapp.model;

import java.sql.Date;

public class OrderHistory {
	private int orderHistoryId;
    private int orderId;
    private int userId;
    private float totalAmount;
    private String status;
    private Date orderdate;
    
    
    // Constructors
    public OrderHistory() {}

    public OrderHistory(int orderHistoryId, int orderId, int userId, float totalAmount, String status,Date orderdate) {
        this.orderHistoryId = orderHistoryId;
        this.userId = userId;
        this.orderId = orderId;
        this.orderdate = orderdate;
        this.totalAmount = totalAmount;
        this.status = status;
    }
    
    public OrderHistory(int orderId, int userId, float totalAmount, String status,Date orderdate) {
        this.userId = userId;
        this.orderId = orderId;
        this.orderdate = orderdate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

	public int getOrderHistoryId() {
		return orderHistoryId;
	}

	public void setOrderHistoryId(int orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderdate;
	}

	public void setOrderDate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderHistory [orderHistoryId=" + orderHistoryId + ", orderId=" + orderId + ", userId=" + userId
				+ ", totalAmount=" + totalAmount + ", status=" + status + ", orderdate=" + orderdate + "]";
	}
	

}
