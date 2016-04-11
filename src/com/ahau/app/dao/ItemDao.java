package com.ahau.app.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ahau.app.dto.UserItemScoreDto;
import com.ahau.app.util.MyBatisUtil;

public class ItemDao {
	private SqlSession session;
	
	public ItemDao(){
		session = MyBatisUtil.getSqlSession();
	}
	
	/**
	 * 查询某用户已评分项
	 * @return
	 */
	public List<UserItemScoreDto> queryItemIntersection(int currentUserId){
		String queryItemIntersection = "com.ahau.mapping.userMapper.queryItemIntersection";
		List<UserItemScoreDto> itemIntersection = session.selectList(queryItemIntersection,currentUserId);
		return itemIntersection;
	}
	
	
	/*public static void main(String[] args) {
		ItemDao itemDao = new ItemDao();
		List<UserItemScoreDto> items = itemDao.queryItemIntersection(1);
		System.out.println("...."+items);
	}*/
	

}
