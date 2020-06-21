package com.brycen.hrm.request;

import java.util.List;

public class EmployeeRequest {
	private int id;
	private String name;
	private String address;
	private String birthday;
	private String phone;
	private String gmail;
	private String skype;
	private String experience;
	private String queQuan;
	private String avatar;
	private boolean sex;
	private boolean married;
	private String finishTraning;
	private String joinCompany;
	private List<DepartmentRequest> department;
	private List<ProjectRequest> project;
	private List<SkillRequest> skills;

	public EmployeeRequest() {

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public String getFinishTraning() {
		return finishTraning;
	}

	public void setFinishTraning(String finishTraning) {
		this.finishTraning = finishTraning;
	}

	public String getJoinCompany() {
		return joinCompany;
	}

	public void setJoinCompany(String joinCompany) {
		this.joinCompany = joinCompany;
	}

	public List<DepartmentRequest> getDepartment() {
		return department;
	}

	public void setDepartment(List<DepartmentRequest> department) {
		this.department = department;
	}

	public List<ProjectRequest> getProject() {
		return project;
	}

	public void setProject(List<ProjectRequest> project) {
		this.project = project;
	}

	public List<SkillRequest> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillRequest> skills) {
		this.skills = skills;
	}

}
