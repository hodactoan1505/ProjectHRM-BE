package com.brycen.hrm.request;

public class ProjectRequest {
    private int id;
    private String name;
    private DepartmentRequest department;
    
    public ProjectRequest() {
        
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

    public DepartmentRequest getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentRequest department) {
        this.department = department;
    }
    
    
}
