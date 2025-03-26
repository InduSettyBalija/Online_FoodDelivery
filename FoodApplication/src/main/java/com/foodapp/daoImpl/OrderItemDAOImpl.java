package com.foodapp.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.OrderItemDAO;
import com.foodapp.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {
	private static Connection con;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private List<OrderItem> orderItemList = new ArrayList<>();
    private static final String INSERT_ORDERITEM = "INSERT INTO orderitem (orderId, menuId, quantity, itemTotal) VALUES (?, ?, ?, ?)";
    private static final String GET_ORDERITEMS_BY_ORDERID = "SELECT * FROM orderitem WHERE orderId=?";

    // Static block to initialize the database connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_application", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderItemDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public int insertOrderhistory(int orderId, int menuId, int quantity, int itemTotal) {
		int x=0;
        try {
            pstmt = con.prepareStatement(INSERT_ORDERITEM);
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, menuId);
            pstmt.setInt(3, quantity);
            pstmt.setInt(4, itemTotal);
            x = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }
	
	@Override
    public int insertOrderhistory(OrderItem orderItem) {
		int result = -1;

	    try {

	    	pstmt = con.prepareStatement(INSERT_ORDERITEM);
	    	pstmt.setInt(1, orderItem.getOrderId());
	        pstmt.setInt(2, orderItem.getMenuId());
	        pstmt.setInt(3, orderItem.getQuantity());
	        pstmt.setInt(4, orderItem.getItemTotal());

	        result = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
    }
	
	

    @Override
    public List<OrderItem> fetchOrderItems(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(GET_ORDERITEMS_BY_ORDERID);
            pstmt.setInt(1, orderId);
            resultSet = pstmt.executeQuery();
            orderItems = extractOrderItemListFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    // Helper method to extract order items from the ResultSet
    private List<OrderItem> extractOrderItemListFromResultSet(ResultSet resultSet) {
        List<OrderItem> orderItemList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                orderItemList.add(new OrderItem(
                        resultSet.getInt("orderitemId"),
                        resultSet.getInt("orderId"),
                        resultSet.getInt("menuId"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("itemTotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

	

	@Override
	public void insertOrderHistory(int userId, int orderId, int totalAmount, String status) {
		// TODO Auto-generated method stub
		
	}

}
