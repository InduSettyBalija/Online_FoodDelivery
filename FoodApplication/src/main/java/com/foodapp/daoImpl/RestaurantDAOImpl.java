package com.foodapp.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.RestaurantDAO;
import com.foodapp.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {
	
	private static Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
	private Restaurant rest;
	int x ;
	
	private static final String INSERT_USER = "insert into `restaurant`(name,cuisineType,deliveryTime,address,isActive,rating,imagePath) VALUES(?,?,?,?,?,?,?)";
	private static final String GET_ALL_restaurantS = "SELECT * FROM restaurant";  // Check table and column names
	private static final String GET_REST_BY_ID = "select * from `restaurant` where restaurantId = ?";
	private static final String UPDATE_ACTIVE_BY_ID = "update `restaurant` set isActive=? where restaurantId = ?";
	private static final String DELETE_restaurant_BY_ID = "delete from `restaurant` where restaurantId = ?";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_application","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insertRestaurant(Restaurant rest) {
	    try {
	        pstmt = con.prepareStatement(INSERT_USER);
	        pstmt.setString(1, rest.getName());
	        pstmt.setString(2, rest.getCuisineType());
	        pstmt.setInt(3, rest.getDeliveryTime());
	        pstmt.setString(4, rest.getAddress());
	        pstmt.setInt(5, rest.getIsActive());
	        pstmt.setFloat(6, rest.getRating());
	        pstmt.setString(7, rest.getImagePath());

	        x = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return x;
	}



	@Override
	public List<Restaurant> getAllRestaurants() {

	    try {
	        stmt = con.createStatement();
	        resultSet = stmt.executeQuery(GET_ALL_restaurantS);

	        restaurantList = (ArrayList<Restaurant>) extractRestListFromResultSet(resultSet);  // No need to cast
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return restaurantList;
	}

@Override
	public Restaurant getRestaurantById(int id) {
    Restaurant restaurant = null;
		try {
			pstmt = con.prepareStatement(GET_REST_BY_ID);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				// Directly retrieve the first record

	            restaurant = new Restaurant(
	                resultSet.getInt("restaurantId"),
	                resultSet.getString("name"),
	                resultSet.getString("cuisineType"),
	                resultSet.getInt("deliveryTime"),
	                resultSet.getString("address"),
	                resultSet.getInt("isActive"),
	                resultSet.getFloat("rating"),
	                resultSet.getString("imagePath")
	            );
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
	}
@Override
	public int updateRestaurantById(int id, int isAvail) {

		try {
			pstmt = con.prepareStatement(UPDATE_ACTIVE_BY_ID);
			pstmt.setInt(2, id);
			pstmt.setInt(1, isAvail);
			x = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
@Override
	public int deleteRestaurantById(int id) {
		
		try {
			pstmt = con.prepareStatement(DELETE_restaurant_BY_ID);
			pstmt.setInt(1, id);
			x = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public List<Restaurant> extractRestListFromResultSet(ResultSet resultSet) {
	    try {
	        while (resultSet.next()) {
	            restaurantList.add(new Restaurant(	
	            		resultSet.getInt(1),
	            		resultSet.getString(2),
	            		resultSet.getString(3),
	            		resultSet.getInt(4),
	            		resultSet.getString(5),
	            		resultSet.getInt(6),
	            		resultSet.getFloat(7),
	            		resultSet.getString(8)
	            		));
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return  restaurantList;
	}


	

}
