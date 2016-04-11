package com.ahau.app.dto;

public class UserSimilarityDto {


	// java bean √¸√˚πÊ‘Ú
	private int cuserId;
	private int ouserId;
	private float similarity;

	
	public UserSimilarityDto() {
	}

	public UserSimilarityDto(int cuserId, int ouserId, float similarity) {
		this.cuserId = cuserId;
		this.ouserId = ouserId;
		this.similarity = similarity;
	}

	public int getCuserId() {
		return cuserId;
	}


	public void setCuserId(int cuserId) {
		this.cuserId = cuserId;
	}


	public int getOuserId() {
		return ouserId;
	}


	public void setOuserId(int ouserId) {
		this.ouserId = ouserId;
	}


	public float getSimilarity() {
		return similarity;
	}


	public void setSimilarity(float similarity) {
		this.similarity = similarity;
	}
	
	@Override
	public String toString() {
		return "UserSimilarityDto [cuserId=" + cuserId + ", ouserId=" + ouserId + ", similarity=" + similarity + "]\n";
	}


	
}
