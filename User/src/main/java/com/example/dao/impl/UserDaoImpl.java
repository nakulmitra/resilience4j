package com.example.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.dao.IUserDao;
import com.example.model.User;

@Repository
public class UserDaoImpl implements IUserDao {

	private static List<User> userList = new ArrayList<>(
			Arrays.asList(new User(1, "Nakul", "Mitra", "nakul.mitra@gmail.com"),
					new User(2, "Piyush", "Mudgal", "piyush.mudgal@gmail.com"),
					new User(3, "Priyanshu", "Sharma", "priyanshu.sharma@gmail.com")));

	@Override
	public User getUserById(Integer id) {
		for(User user : userList) {
			if(user.getId().equals(id)) {
				return user;
			}
		}
		
		return null;
	}

}
