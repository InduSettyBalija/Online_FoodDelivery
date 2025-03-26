package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.User;

public interface UserDAO {
	
		
		List<User> getAllUsers();
		User getUserById(String email);
		int updateUserById(int userId,String address);
		int deleteUserById(int userId);
		int insertUser(User user);
	

}
