package com.ahau.app.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ahau.app.entity.Tag;
import com.ahau.app.util.MyBatisUtil;

public class TagDao {
	private SqlSession session;

	public TagDao() {
		session = MyBatisUtil.getSqlSession();
	}

	/**
	 * �����û�id��ѯ�û���ע�ı�ǩ
	 * @param userId
	 * @return
	 */
	public List<Tag> getTags(int userId) {
		String getTags = "com.ahau.mapping.tagMapper.getTags";
		List<Tag> tags = session.selectList(getTags, userId);
		return tags;
	}

}
