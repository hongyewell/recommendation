package com.ahau.app.entity;

public class Tag {
	
	private int id;
	private String tag_name;
	
	public Tag() {
	}

	public Tag(int id, String tagName) {
		this.id = id;
		this.tag_name = tagName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return tag_name;
	}
	public void setTagName(String tagName) {
		this.tag_name = tagName;
	}
	
	@Override
	public String toString() {
		return "Tag [id=" + id + ", tagName=" + tag_name + "]";
	}
}
