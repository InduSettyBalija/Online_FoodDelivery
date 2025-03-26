package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.Menu;

public interface MenuDAO {
	int insertMenu(Menu m);
	List<Menu> getAllRestMenu(int restaurantId);
	Menu getMenuById(int menuId);
	int updateMenuAvailById(int menuId, int isAvailable);
	int deleteMenuById(int menuId);

}
