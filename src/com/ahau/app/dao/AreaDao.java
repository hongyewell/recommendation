package com.ahau.app.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ahau.app.entity.Area;
import com.ahau.app.util.MyBatisUtil;

public class AreaDao {
	private SqlSession session;

	public AreaDao() {
		session = MyBatisUtil.getSqlSession();
	}

	/**
	 * 根据用户id查询用户所在区域
	 * @param userId
	 * @return
	 */
	public Area getAreaById(int userId) {
		String getArea = "com.ahau.mapping.areaMapper.getArea";
		Area area = session.selectOne(getArea, userId);
		return area;
	}
	
	/**
	 * 查询两个地区 的相似度
	 * @param areaCode
	 * @param areaOtherCode
	 * @return
	 */
	public int queryAreaSimilarity(String areaCode, String areaOtherCode) {
		String queryAreaSimilarity = "com.ahau.mapping.areaMapper.queryAreaSimilarity";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("areaCode", areaCode);
		params.put("areaOtherCode", areaOtherCode);
		Integer similarity = session.selectOne(queryAreaSimilarity, params);
		if(similarity == null) {
			return 0;
		}
//		int s = similarity.intValue(); // 拆箱
		return similarity;
	}

}
