package com.brycen.hrm.response.object;

public class UserResponse extends BaseResponse {
    private String username;
    private RoleResponse role;
    private DepartmentResponse department;
    private ProjectResponse project;
    private String name;
    private int id;

    public UserResponse() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleResponse getRole() {
        return role;
    }

    public void setRole(RoleResponse role) {
        this.role = role;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
