package com.ahau.app.entity;

import java.util.List;

public class User {
	
	private Integer id;
	
	private String name;
	/**
	 * �û���ע�Ľ�ɫ��ǩ
	 */
	private List<Tag> tags;
	
	/**
	 * �û����ڵ���
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
