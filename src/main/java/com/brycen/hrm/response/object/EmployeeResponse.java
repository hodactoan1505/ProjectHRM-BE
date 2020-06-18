package com.brycen.hrm.response.object;

import java.util.List;

public class EmployeeResponse extends BaseResponse {
    private int id;
    private String name;
    private String phone;
    private String skype;
    private String gmail;
    private DepartmentResponse department;
    private ProjectResponse project;
    private List<SkillResponse> skills;

    public EmployeeResponse() {

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public DepartmentResponse getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentResponse department) {
        this.department = department;
    }

    public ProjectResponse getProject() {
        return project;
    }

    public void setProject(ProjectResponse project) {
        this.project = project;
    }

    public List<SkillResponse> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillResponse> skills) {
        this.skills = skills;
    }

}
