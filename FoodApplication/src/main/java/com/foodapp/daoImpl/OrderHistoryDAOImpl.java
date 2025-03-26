package com.foodapp.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.OrderHistoryDAO;
import com.foodapp.model.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO{
	private static Connection con;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private static OrderHistory orderHistory;
    private ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();
    private int x = -1;

    private static final String ORDER_HISTORY_BY_ID = "SELECT * FROM orderhistory WHERE orderHistoryId = ?";
    private static final String INSERT_ORDER_HISTORY = "INSERT INTO orderhistory (orderHistoryId, userId, orderId, orderdate, totalAmount, status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER_HISTORY_STATUS = "UPDATE orderhistory SET status = ? WHERE orderHistoryId = ?";
    private static final String GET_ORDER_HISTORY_BY_USER_ID = "SELECT * FROM orderhistory WHERE userId = ?";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_application", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderHistory fetchOrderHistoryById(int orderHistoryId) {
        try {
            pstmt = con.prepareStatement(ORDER_HISTORY_BY_ID);
            pstmt.setInt(1, orderHistoryId);
            resultSet = pstmt.executeQuery();

            orderHistoryList = (ArrayList<OrderHistory>) extractOrderHistoryListFromResultSet(resultSet);
            orderHistory = orderHistoryList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistory;
    }

    @Override
    public int insertOrderHistory(OrderHistory orderHistory) {
        try {
            pstmt = con.prepareStatement(INSERT_ORDER_HISTORY);
            pstmt.setInt(1, orderHistory.getOrderHistoryId());
            pstmt.setInt(2, orderHistory.getUserId());
            pstmt.setInt(3, orderHistory.getOrderId());
            pstmt.setDate(4, orderHistory.getOrderDate());
            pstmt.setFloat(5, orderHistory.getTotalAmount());
            pstmt.setString(6, orderHistory.getStatus());

            x = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    @Override
    public int updateOrderHistoryStatusById(int orderHistoryId, String status) {
        try {
            pstmt = con.prepareStatement(UPDATE_ORDER_HISTORY_STATUS);
            pstmt.setString(1, status);
            pstmt.setInt(2, orderHistoryId);

            x = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    @Override
    public List<OrderHistory> fetchOrderHistoryByUserId(int userId) {
        try {
            pstmt = con.prepareStatement(GET_ORDER_HISTORY_BY_USER_ID);
            pstmt.setInt(1, userId);
            resultSet = pstmt.executeQuery();

            orderHistoryList = (ArrayList<OrderHistory>) extractOrderHistoryListFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    private List<OrderHistory> extractOrderHistoryListFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                orderHistoryList.add(new OrderHistory(
                		resultSet.getInt("orderHistoryId"),
                        resultSet.getInt("orderId"),
                        resultSet.getInt("userId"),
                        resultSet.getFloat("totalAmount"),
                        resultSet.getString("status"),
                        resultSet.getDate("orderdate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

	@Override
	public int insertOrderHistory(OrderHistoryDAO orderHistory) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
