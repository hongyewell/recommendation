package com.ahau.app.entity;

public class Area {
	
	private int id;
	private String area_code;
	private String area_name;
	
	

	public Area() {
	}
	
	public Area(int id, String code, String name) {
		this.id = id;
		this.area_code = code;
		this.area_name = name;
	}

	public Area(String code, String name) {
		this.area_code = code;
		this.area_name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return area_code;
	}

	public void setCode(String code) {
		this.area_code = code;
	}

	public String getName() {
		return area_name;
	}

	public void setName(String name) {
		this.area_name = name;
	}
	
	@Override
	public String toString() {
		return "Area [id=" + id + ", code=" + area_code + ", name=" + area_name + "]";
	}
}
