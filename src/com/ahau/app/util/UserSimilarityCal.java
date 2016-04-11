package com.ahau.app.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.ahau.app.dao.AreaDao;
import com.ahau.app.dao.ItemDao;
import com.ahau.app.dao.TagDao;
import com.ahau.app.dao.UserDao;
import com.ahau.app.dto.UserItemScoreDto;
import com.ahau.app.dto.UserSimilarityDto;
import com.ahau.app.entity.Area;
import com.ahau.app.entity.Tag;
import com.ahau.app.entity.User;

/**
 * 用户相似度计算
 * @author Administrator
 *
 */
public class UserSimilarityCal {
	
	// 模拟数据
/*	public void initData(User currentUser, List<User> otherUsers) {
		// 当前用户信息
		currentUser.setArea(new Area("340321", "怀远县"));
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(new Tag(1, "水稻种植"));
		tags.add(new Tag(2, "棉花种植"));
		currentUser.setTags(tags);
		
		// 查询其他用户信息
		User otherUser = new User();
		otherUser.setId(2);
		otherUser.setArea(new Area("342529", "泾县"));
		List<Tag> otherTags = new ArrayList<Tag>();
		otherTags.add(new Tag(1, "水稻种植"));
		otherUser.setTags(otherTags);
		
		otherUsers.add(otherUser);
		
	}*/
	
	private static UserDao userDao;
	private static AreaDao areaDao;
	private static TagDao tagDao;
	private static ItemDao itemDao;

	public UserSimilarityCal() {
		userDao = new UserDao();
		areaDao = new AreaDao();
		tagDao = new TagDao();
		itemDao = new ItemDao();
	}
	
	
	/**
	 * 计算用户相似度
	 * @param currentUser
	 * @param otherUsers
	 */
	public void calculateSimilarity(User currentUser , List<User> otherUsers) {
		//User currentUser = new User();
		//currentUser.setId(userId);
		
		//List<User> otherUsers = new ArrayList<User>();
		
		// 模拟数据
	//	initData(currentUser, otherUsers);
		
		// 计算指标(用户关注的角色标签, 地区)
		for (User u : otherUsers ) {
			// 地区相似度
			float s = 0;
			if(u.getArea().getCode().equals(currentUser.getArea().getCode())) {
				s = 1;
			}else {
				// 查询两个不同地区的相似度
				Area currentArea = currentUser.getArea();
				Area otherArea = u.getArea();
				if(currentArea == null || otherArea == null) {
					continue;
				}
				s = areaDao.queryAreaSimilarity(currentArea.getCode(), otherArea.getCode());
			
//				s = 0.8F;
			}

			List<Tag> currentUserTags = currentUser.getTags();
			List<Tag> otherUserTags = u.getTags();
			// 角色标签相似度	
			int count = getIntersection(currentUserTags, otherUserTags);
			// System.out.println("s....."+s);
			
			double tolSimilarity = s * 0.3 + count * 0.7;  

			DecimalFormat    df   = new DecimalFormat("######0.00");   

			String sim = df.format(tolSimilarity);
			float simi = Float.valueOf(sim);
			
			System.out.println(currentUser.getId() + " --> " + u.getId() + " -->" + df.format(tolSimilarity));
			
			
			UserSimilarityDto dto = new UserSimilarityDto(currentUser.getId(), u.getId(), simi);
			userDao.saveUserSimilarity(dto );
			
		}
		
	}
	
	//计算角色标签相似度
	private int getIntersection(List<Tag> currentTags, List<Tag> otherTags) {
		if(currentTags == null || currentTags.isEmpty()) {
			return 0;
		}
		if(otherTags == null || otherTags.isEmpty()) {
			return 0;
		}
		
		int count = 0;
		for(Tag ct : currentTags) {
			for (Tag ot : otherTags) {
				if(ct.getId() == ot.getId()) {
					count = count + 1;
				}
			}
		}
		// System.out.println("count....."+count);
		return count;
	}
	
	/**
	 * 筛选邻近用户候选集
	 * @param currentUserId
	 */
	public List<UserSimilarityDto> selectUserSimilarity(int currentUserId) {
		int userCount = userDao.queryUserCount(); //用户总数
//		System.out.println("userCount....."+userCount);
		
		int limitUserCount = (int) (userCount*0.8); //用户总数的80%
//		System.out.println("limitUserCount....."+limitUserCount); 
		
		List<UserSimilarityDto> limitUserList = userDao.queryLimitUserList(currentUserId, limitUserCount); //查询近邻用户候选集
//		System.out.println("limitUserList....."+limitUserList);
		
		return limitUserList;
	}
	
	/**
	 * 查询当前用户与准近邻用户的共同评分项
	 */
	public List<Integer> queryItemIntersection(int currentUserId, List<UserSimilarityDto> limitUserList){
		
		//当前用户已评分项
		List<UserItemScoreDto>  cItems = itemDao.queryItemIntersection(currentUserId);
		//System.out.println("cItems....."+cItems);
		
		
		/*List<Integer> cItemsId = new ArrayList<Integer>();
		for(UserItemScoreDto c : cItems){
			cItemsId.add(c.getPesticideId());
		}
		System.out.println("cItemsId....."+cItemsId);*/
		
		
		for(UserSimilarityDto u : limitUserList){
			int ouserId = u.getOuserId();
			//查询准近邻用户已评分项
			List<UserItemScoreDto>  oItems = itemDao.queryItemIntersection(ouserId);
			//System.out.println("oItems....."+oItems);
			
			
			/*List<Integer> oItemsId = new ArrayList<Integer>();
			for(UserItemScoreDto o : oItems){
				oItemsId.add(o.getPesticideId());
			}*/
			//System.out.println(ouserId+"....."+oItemsId);
			
			//寻找共同评分项
			//List<UserItemScoreDto> intersectionItemsId = new ArrayList<UserItemScoreDto>();
			int x = 0;
			int y = 0;
			int z = 0;
			for(UserItemScoreDto i : cItems){
				for(UserItemScoreDto j :oItems ){
					if(i.getPesticideId() == j.getPesticideId()){
						//System.out.println(ouserId+"....intemIntersection...."+i);
						//intersectionItemsId.add(i);
						int iScore = i.getGrade();
						int jScore = j.getGrade();
						x += iScore*jScore;
						y += iScore*iScore;
						z += jScore*jScore;
						
						float result = x/y*z;
					}
				}
			}
			
			//System.out.println(ouserId+"....."+intersectionItemsId);
			
			//根据余弦相似度公式计算目标用户与准近邻用户相似度
			
			
			
		}
		
		
		return null;
		
	}

	public static void main(String[] args) {
		UserSimilarityCal  u = new UserSimilarityCal();
		List<UserSimilarityDto> limitUserList = u.selectUserSimilarity(2);
		u.queryItemIntersection(2, limitUserList);
	}
	
	
	
}
