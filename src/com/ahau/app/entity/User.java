package com.ahau.app.entity;

import java.util.List;

public class User {
	
	private Integer id;
	
	private String name;
	/**
	 * 用户关注的角色标签
	 */
	private List<Tag> tags;
	
	/**
	 * 用户所在地区
	 */
	private Area area;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", tags=" + tags + ", area=" + area + "]";
	}
}
