package com.brycen.hrm.response.object;

public class SkillResponse extends BaseResponse {
	private int id;
	private String name;

	public SkillResponse() {

	}

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

}
