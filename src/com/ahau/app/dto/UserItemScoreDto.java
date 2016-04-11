package com.ahau.app.dto;

public class UserItemScoreDto {

	
	private int userId;
	private int pesticideId;
	private int grade;
	
	public UserItemScoreDto(){
		
	}
	public UserItemScoreDto(int userId, int pesticideId, int grade) {
		this.userId = userId;
		this.pesticideId = pesticideId;
		this.grade = grade;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPesticideId() {
		return pesticideId;
	}
	public void setPesticideId(int pesticideId) {
		this.pesticideId = pesticideId;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "UserItemScoreDto [userId=" + userId + ", pesticideId=" + pesticideId + ", grade=" + grade + "]";
	}
	
	
	
}
