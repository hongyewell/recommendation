package com.ahau.app.test;

import java.util.List;

import com.ahau.app.dao.AreaDao;
import com.ahau.app.dao.TagDao;
import com.ahau.app.dao.UserDao;
import com.ahau.app.entity.Area;
import com.ahau.app.entity.Tag;
import com.ahau.app.entity.User;
import com.ahau.app.util.UserSimilarityCal;

public class MyTest {

	private static UserDao userDao;
	private static AreaDao areaDao;
	private static TagDao tagDao;

	public static void init() {
		userDao = new UserDao();
		areaDao = new AreaDao();
		tagDao = new TagDao();
	}

	public static void main(String[] args) {
		
		init();
		
		List<User> users = userDao.queryAllUser();
		
		if(users == null || users.isEmpty()) {
			return;
		}
		
		// ����û����ƶ�����
		userDao.deleteUserSimilarity();
				
		
		for (User user : users) {
			cal(user.getId());
		}
		
		
		//cal(1);
		
	}
	
	
	public static void cal(int currentUserId) {
	
		// //ִ�в�ѯ����һ��Ψһuser�����sql
		User currentUser = userDao.getUserById(currentUserId);
		Area area = areaDao.getAreaById(currentUserId);

		List<Tag> tags = tagDao.getTags(currentUserId);

		// ��ǰ�û���Ϣ
		currentUser.setArea(area);
		currentUser.setTags(tags);

		// �����û���Ϣ
		List<User> otherUsers = userDao.getOtherUsers(currentUserId);
		if (otherUsers == null || otherUsers.isEmpty()) {
			return;
		}
		for (User u : otherUsers) {
			int id = u.getId();
			Area otherArea = areaDao.getAreaById(id);
			List<Tag> otherTags = tagDao.getTags(id);
			u.setArea(otherArea);
			u.setTags(otherTags);
		}

		UserSimilarityCal cal = new UserSimilarityCal();
		cal.calculateSimilarity(currentUser, otherUsers);

	}
}
