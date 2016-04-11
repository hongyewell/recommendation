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
 * �û����ƶȼ���
 * @author Administrator
 *
 */
public class UserSimilarityCal {
	
	// ģ������
/*	public void initData(User currentUser, List<User> otherUsers) {
		// ��ǰ�û���Ϣ
		currentUser.setArea(new Area("340321", "��Զ��"));
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(new Tag(1, "ˮ����ֲ"));
		tags.add(new Tag(2, "�޻���ֲ"));
		currentUser.setTags(tags);
		
		// ��ѯ�����û���Ϣ
		User otherUser = new User();
		otherUser.setId(2);
		otherUser.setArea(new Area("342529", "����"));
		List<Tag> otherTags = new ArrayList<Tag>();
		otherTags.add(new Tag(1, "ˮ����ֲ"));
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
	 * �����û����ƶ�
	 * @param currentUser
	 * @param otherUsers
	 */
	public void calculateSimilarity(User currentUser , List<User> otherUsers) {
		//User currentUser = new User();
		//currentUser.setId(userId);
		
		//List<User> otherUsers = new ArrayList<User>();
		
		// ģ������
	//	initData(currentUser, otherUsers);
		
		// ����ָ��(�û���ע�Ľ�ɫ��ǩ, ����)
		for (User u : otherUsers ) {
			// �������ƶ�
			float s = 0;
			if(u.getArea().getCode().equals(currentUser.getArea().getCode())) {
				s = 1;
			}else {
				// ��ѯ������ͬ���������ƶ�
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
			// ��ɫ��ǩ���ƶ�	
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
	
	//�����ɫ��ǩ���ƶ�
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
	 * ɸѡ�ڽ��û���ѡ��
	 * @param currentUserId
	 */
	public List<UserSimilarityDto> selectUserSimilarity(int currentUserId) {
		int userCount = userDao.queryUserCount(); //�û�����
//		System.out.println("userCount....."+userCount);
		
		int limitUserCount = (int) (userCount*0.8); //�û�������80%
//		System.out.println("limitUserCount....."+limitUserCount); 
		
		List<UserSimilarityDto> limitUserList = userDao.queryLimitUserList(currentUserId, limitUserCount); //��ѯ�����û���ѡ��
//		System.out.println("limitUserList....."+limitUserList);
		
		return limitUserList;
	}
	
	/**
	 * ��ѯ��ǰ�û���׼�����û��Ĺ�ͬ������
	 */
	public List<Integer> queryItemIntersection(int currentUserId, List<UserSimilarityDto> limitUserList){
		
		//��ǰ�û���������
		List<UserItemScoreDto>  cItems = itemDao.queryItemIntersection(currentUserId);
		//System.out.println("cItems....."+cItems);
		
		
		/*List<Integer> cItemsId = new ArrayList<Integer>();
		for(UserItemScoreDto c : cItems){
			cItemsId.add(c.getPesticideId());
		}
		System.out.println("cItemsId....."+cItemsId);*/
		
		
		for(UserSimilarityDto u : limitUserList){
			int ouserId = u.getOuserId();
			//��ѯ׼�����û���������
			List<UserItemScoreDto>  oItems = itemDao.queryItemIntersection(ouserId);
			//System.out.println("oItems....."+oItems);
			
			
			/*List<Integer> oItemsId = new ArrayList<Integer>();
			for(UserItemScoreDto o : oItems){
				oItemsId.add(o.getPesticideId());
			}*/
			//System.out.println(ouserId+"....."+oItemsId);
			
			//Ѱ�ҹ�ͬ������
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
			
			//�����������ƶȹ�ʽ����Ŀ���û���׼�����û����ƶ�
			
			
			
		}
		
		
		return null;
		
	}

	public static void main(String[] args) {
		UserSimilarityCal  u = new UserSimilarityCal();
		List<UserSimilarityDto> limitUserList = u.selectUserSimilarity(2);
		u.queryItemIntersection(2, limitUserList);
	}
	
	
	
}
