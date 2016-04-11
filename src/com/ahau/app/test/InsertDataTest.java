package com.ahau.app.test;

import java.util.List;

import com.ahau.app.dao.UserDao;
import com.ahau.app.entity.User;

public class InsertDataTest {
	
	public static void main(String[] args) {
		
		UserDao userDao = new UserDao();
		List<User> userList = userDao.queryAllUser();
		
		for(User u : userList) {
			int userId = u.getId();
			for(int i = 1; i <= 3; i++) {
				int pesticideId = i;
				int score = (int)(Math.random() * 6);  // [0, 1) * 5 => [0, 6)
				System.out.println(userId+"......"+pesticideId+"......."+score);
				userDao.insertUserScore(userId, pesticideId, score);
			}
			
		}
		
	/*	for( int i = 0; i < 10; i++) {
			int score = (int)(Math.random() * 6);  // [0, 1) * 5 => [0, 6)
			System.out.println(score);
		}*/
		
		
		
		
	}

}
