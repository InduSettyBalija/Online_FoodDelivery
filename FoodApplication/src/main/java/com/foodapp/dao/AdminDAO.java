package com.foodapp.dao;

import com.foodapp.model.Admin;

public interface AdminDAO {
	Admin  getAdminByEmail(String Email);
}

