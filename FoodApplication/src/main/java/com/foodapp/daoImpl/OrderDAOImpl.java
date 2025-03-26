package com.foodapp.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import com.foodapp.dao.OrderDAO;
import com.foodapp.model.Order;

public class OrderDAOImpl implements OrderDAO{
	
	private static Connection con;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private List<Order> ordersList = new ArrayList<>();
    private static final String INSERT_ORDER = "INSERT INTO orders (userId, restaurantId, totalAmount, status, paymentOption, orderdate) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_ORDERS = "SELECT * FROM orders";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE orderId=?";
    private static final String DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE orderId=?";
    private static final String UPDATE_ORDER_STATUS_BY_ID = "UPDATE orders SET status=? WHERE orderId=?";
    
    // Static block to initialize the database connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_application?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&rewriteBatchedStatements=true", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public int insertOrder(Order order) {
        int result = -1;
        ResultSet rs = null;
        int generatedOrderId = -1;
        try {
            pstmt = con.prepareStatement(INSERT_ORDER,new String[]{"orderId"});
            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getRestaurantId());
            pstmt.setInt(3, order.getTotalAmount());
            pstmt.setString(4, order.getStatus());
            pstmt.setString(5, order.getPaymentOption());
            
            Timestamp orderTimestamp = new Timestamp(System.currentTimeMillis());
            pstmt.setTimestamp(6, orderTimestamp);
            order.setDate(orderTimestamp);
            
            result = pstmt.executeUpdate();
            
            if (result > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedOrderId = rs.getInt(1); // Get the generated orderId
                    order.setOrderId(generatedOrderId); // Set orderId in the object
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedOrderId;
    }

    @Override
    public List<Order> getAllOrders() {
        try {
            Statement stmt = con.createStatement();
            resultSet = stmt.executeQuery(GET_ALL_ORDERS);
            ordersList = extractOrderListFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        try {
            pstmt = con.prepareStatement(GET_ORDER_BY_ID);
            pstmt.setInt(1, orderId);
            resultSet = pstmt.executeQuery();
            
            if (resultSet.next()) {
            	Timestamp orderTimestamp = resultSet.getTimestamp("orderdate");
                if (orderTimestamp == null) { // FIX: Prevent NullPointerException
                    orderTimestamp = new Timestamp(System.currentTimeMillis());
                }
                order = new Order(resultSet.getInt("orderId"), resultSet.getInt("userId"),
                                  resultSet.getInt("restaurantId"), resultSet.getInt("totalAmount"),
                                  resultSet.getString("status"), resultSet.getTimestamp("orderdate"),
                                  resultSet.getString("paymentOption"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public int updateOrderStatusById(int orderId, String status) {
        int result = -1;
        try {
            pstmt = con.prepareStatement(UPDATE_ORDER_STATUS_BY_ID);
            pstmt.setString(1, status);
            pstmt.setInt(2, orderId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteOrderById(int orderId) {
        int result = -1;
        try {
            pstmt = con.prepareStatement(DELETE_ORDER_BY_ID);
            pstmt.setInt(1, orderId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    // Helper method to extract orders from the ResultSet
    private List<Order> extractOrderListFromResultSet(ResultSet resultSet) {
        List<Order> orderList = new ArrayList<>();
        try {
            while (resultSet.next()) {
            	Timestamp orderTimestamp = resultSet.getTimestamp("orderdate");
                if (orderTimestamp == null) { // Prevent NullPointerException
                    orderTimestamp = new Timestamp(System.currentTimeMillis());
                }
                orderList.add(new Order(resultSet.getInt("orderId"),
                                        resultSet.getInt("userId"),
                                        resultSet.getInt("restaurantId"),
                                        resultSet.getInt("totalAmount"),
                                        resultSet.getString("status"),
                                        orderTimestamp,
                                        resultSet.getString("paymentOption")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

	@Override
	public Order fetchOrderOnUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertOrder(int userId, int restaurantId, int totalAmount, String status, String paymentOption) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrder(int orderId, String status) {
		// TODO Auto-generated method stub
		return false;
	}


}
