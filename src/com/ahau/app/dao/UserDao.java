package com.ahau.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ahau.app.dto.UserSimilarityDto;
import com.ahau.app.entity.User;
import com.ahau.app.util.MyBatisUtil;

public class UserDao {
	
	private SqlSession session;

	public UserDao() {
		session = MyBatisUtil.getSqlSession();
	}
	
	/**
	 * �����û�id��ѯ
	 * @param id
	 * @return
	 */
	public User getUserById(int id) {
		String getUser = "com.ahau.mapping.userMapper.getUser";//ӳ��sql�ı�ʶ�ַ���
	    //ִ�в�ѯ����һ��Ψһuser�����sql
	    return session.selectOne(getUser, id);
	}
	
	/**
	 * ���ݵ�ǰ�û�id��ѯ���������û���Ϣ
	 * @param currentUserId
	 * @return
	 */
	public List<User> getOtherUsers(int currentUserId) {
		String getOtherUsers = "com.ahau.mapping.userMapper.getOtherUsers";
		List<User> otherUsers = session.selectList(getOtherUsers, currentUserId);
		return otherUsers;
	}
	

	/**
	 * ��ѯ�����û���Ϣ
	 * @return
	 */
	public List<User> queryAllUser() {
		String queryAllUser = "com.ahau.mapping.userMapper.queryAllUser";
		List<User> users = session.selectList(queryAllUser);
		return users;
	}
	
	/**
	 * �����û����ƶ�
	 * @param dto
	 */
	public void saveUserSimilarity(UserSimilarityDto dto) {
		String saveUserSimilarity = "com.ahau.mapping.userMapper.saveUserSimilarity";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cuserId", dto.getCuserId());
		params.put("ouserId", dto.getOuserId());
		params.put("similarity", dto.getSimilarity());
		
		session.insert(saveUserSimilarity, params);
		session.commit();
	}
	
	public void deleteUserSimilarity() {
		// ��ձ�����
		String deleteUserSimilarity = "com.ahau.mapping.userMapper.deleteUserSimilarity";
		session.delete(deleteUserSimilarity);
		session.commit();
	}
	
	/**
	 * ��ѯ�û�����
	 * @return
	 */
	public int queryUserCount(){
		String queryUserCount = "com.ahau.mapping.userMapper.queryUserCount";
		Integer userCount =  session.selectOne(queryUserCount);
		return userCount;
	}
	
	/**
	 * ��ѯ�����û���ѡ��
	 * @return
	 */
	public List<UserSimilarityDto> queryLimitUserList(int currentUserId ,int limitUserCount){
		String queryLimitUserList = "com.ahau.mapping.userMapper.queryLimitUserList";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", currentUserId);
		params.put("limitUserCount", limitUserCount);
		List<UserSimilarityDto> limitUserList = session.selectList(queryLimitUserList, params);
		return limitUserList;
		
	}
	
	/**
	 * ģ������û���������
	 * @param userId
	 * @param pesticideId
	 * @param grade
	 */
	public void insertUserScore(int userId, int pesticideId,int grade){
		String insertUserScore = "com.ahau.mapping.userMapper.insertUserScore";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("pesticideId", pesticideId);
		params.put("grade", grade);
		session.insert(insertUserScore, params);
		session.commit();
	}

}
